package com.example.springwebtask.controller;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;
import com.example.springwebtask.entity.UserFind;
import com.example.springwebtask.service.CategoriesService;
import com.example.springwebtask.service.ProductsService;
import com.example.springwebtask.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/login")
    public String login(@ModelAttribute("login") UserFind userFind) {
        return "index";
    }

    @PostMapping("login")
    public String isLogin(@Validated @ModelAttribute("login") UserFind userFind, BindingResult bindingResult, Model model) {
        List<UserFind> isLogin = userService.findUser(userFind.getLoginId(), userFind.getPassword());
        if (bindingResult.hasErrors()){
            return "index";
        }
        if (isLogin == null) {
            model.addAttribute("message","IDまたはパスワードが不正です");
            return "index";
        } else {
            model.addAttribute("menu", isLogin);
            session.setAttribute("user",isLogin.get(0));    //セッションメニューhtmlを書き次第解禁
            return "redirect:/menu";
        }
    }

    @GetMapping("/menu")
    public String getMenu(@ModelAttribute("menu") UserFind userFind,Model model) {
        model.addAttribute("products", productsService.findAll());
        return "menu";
    }

    @GetMapping("logout.html")
    public String logout(){
        return "redirect:/logout";
    }
    @GetMapping("/logout")
    public String logoutAuto(){
        session.invalidate();
        return "logout";
    }

    @GetMapping("/insert")
    public String insertGet(@ModelAttribute("insert")ProductsFind productsFind, Model model){
        model.addAttribute("categories", categoriesService.findAll());
        return "insert";
    }

    @PostMapping("insert")
    public String insertPost(@RequestParam("categoryName")String category,@Validated @ModelAttribute("insert")ProductsFind productsFind,
                             BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("categories", categoriesService.findAll());
            return "insert";
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ProductsRecord pr = new ProductsRecord(-1,productsFind.getProductId(),Integer.parseInt(productsFind.getCategoryName()),
                productsFind.getName(),productsFind.getPrice(), "",productsFind.getDescription(),timestamp, timestamp);
        int up = productsService.insert(pr);
        if (up == 0){
            model.addAttribute("categories", categoriesService.findAll());
            model.addAttribute("message","商品IDが重複しています");
            return "insert";
        }
        model.addAttribute("categories", categoriesService.findAll());
        model.addAttribute("message","登録が完了しました");
        return "insert";
    }



}
