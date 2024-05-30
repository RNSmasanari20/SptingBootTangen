package com.example.springwebtask.controller;

import com.example.springwebtask.entity.*;
import com.example.springwebtask.service.CategoriesService;
import com.example.springwebtask.service.ProductsService;
import com.example.springwebtask.service.UserService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;

import jakarta.validation.constraints.AssertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    UserService userService;

    @Autowired
    ProductsService productsService;

    @Autowired
    CategoriesService categoriesService;

    @Autowired
    private HttpSession session;

    public String successMes;

    @GetMapping("/login")
    public String login(@ModelAttribute("login") UserFind userFind) {
        return "index";
    }

    @PostMapping("login")
    public String isLogin(@Validated @ModelAttribute("login") UserFind userFind, BindingResult bindingResult, Model model) {
        List<UserFind> isLogin = userService.findUser(userFind.getLoginId(), userFind.getPassword());
        if (bindingResult.hasErrors()) {
            return "index";
        }
        if (isLogin == null) {
            model.addAttribute("message", "IDまたはパスワードが不正です");
            return "index";
        } else {
            model.addAttribute("menu", isLogin);
            session.setAttribute("user", isLogin.get(0));    //セッションメニューhtmlを書き次第解禁
//            System.out.println(isLogin.get(0).getRole());
            return "redirect:/menu";
        }
    }

    @GetMapping("/menu")
    public String getMenu(@ModelAttribute("search") UserFind userFind, Model model) {
        if((session.getAttribute("user") == null)){
            return "redirect:/login";
        }

        String mes = successMes;
        successMes = "";
        model.addAttribute("products", productsService.findAll());
        model.addAttribute("message", mes);
        return "menu";

    }

    @GetMapping("logout.html")
    public String logout() {
        return "redirect:/logout";
    }

    @PostMapping("/menu")
    public String search(@ModelAttribute("search") UserFind userFind, Model model) {
//        System.out.println(userFind.getName());
        if (userFind.getName() == null) {
            model.addAttribute("products", productsService.findAll());
            return "menu";
        }
        model.addAttribute("products", productsService.searchProduct(userFind.getName()));
        return "menu";
    }

    @GetMapping("/logout")
    public String logoutAuto() {
        session.invalidate();
        return "logout";
    }

    @GetMapping("/insert")
    public String insertGet(@ModelAttribute("insert") ProductsFind productsFind, Model model) {
        model.addAttribute("categories", categoriesService.findAll());
        return "insert";
    }

    @PostMapping("insert")
    public String insertPost(@RequestParam("categoryName") String category, @Validated @ModelAttribute("insert") ProductsFind productsFind,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoriesService.findAll());
            return "insert";
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ProductsRecord pr = new ProductsRecord(-1, productsFind.getProductId(), Integer.parseInt(productsFind.getCategoryName()),
                productsFind.getName(), productsFind.getPrice(), "", productsFind.getDescription(), timestamp, timestamp);
        int up = productsService.insert(pr);
        if (up == 0) {
            model.addAttribute("categories", categoriesService.findAll());
            model.addAttribute("message", "商品IDが重複しています");
            return "insert";
        }
        ProductsFind reset = new ProductsFind();

        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("insert", reset);
        model.addAttribute("message", "登録が完了しました");
        return "insert";
    }


    @GetMapping("/detail/{id}")
    public String detailProduct(@PathVariable("id") int i, @ModelAttribute("products") ProductsFind productsFind, Model model) {
        model.addAttribute("detail", productsService.detailProduct(i));
        return "detail";
    }

    //detailに送る用のデータをDBから取り出すメソッドは作成済み。取得した値を
    //model.addAttribute("detailファイルのth:objectに一致する値", productsService.detailProduct(str));
    //に送信する。
    @PostMapping("/detail/{id}")
    public String deleteProduct(@PathVariable("id") int i,@ModelAttribute("detail") ProductsFind productsFind, Model model) {
//        System.out.println(productsFind.getProductId());
        int deleteCheck = productsService.deleteProduct(productsFind.getProductId());
        if (deleteCheck == 0) {
            model.addAttribute("detail", productsService.detailProduct(productsFind.getProductId()));
            model.addAttribute("message", "削除に失敗しました");
            return "detail";
        }
        model.addAttribute("products", productsService.findAll());
        successMes = "削除に成功しました";
        return "redirect:/menu";
    }


    @RequestMapping(value = "/detail/{id}", params = "update", method = RequestMethod.POST)
    public String updateMove(@ModelAttribute("detail") ProductsFind productsFind, Model model) {
        model.addAttribute("update", productsFind);
        return "redirect:/detail/update/{id}";
    }

    @GetMapping("/detail/update/{id}")
    public String updateProduct(@PathVariable("id") int i,@ModelAttribute("update") ProductsFind productsFind, Model model) {
        model.addAttribute("update", productsService.detailProduct(i));
        model.addAttribute("categories", categoriesService.findAll());
        return "updateInput";
    }

    @PostMapping("/detail/update/{id}")
    public String upDb(@PathVariable("id") int id,@Validated @ModelAttribute("update") ProductsFind productsFind, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoriesService.findAll());
            return "updateInput";
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        ProductsRecord pr = new ProductsRecord(id, productsFind.getProductId(), Integer.parseInt(productsFind.getCategoryName()),
                productsFind.getName(), productsFind.getPrice(), "", productsFind.getDescription(), timestamp, timestamp);
        int b =productsService.updateProduct(pr);

        if (b == 0){

            model.addAttribute("categories", categoriesService.findAll());
            model.addAttribute("message", "商品IDが重複しています");
            return "updateInput";
        }

        model.addAttribute("categories", categoriesService.findAll());
        successMes = "更新に成功しました";
        return "redirect:/menu";
    }


}
