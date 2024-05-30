package com.example.springwebtask;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;
import com.example.springwebtask.entity.UserFind;
import com.example.springwebtask.entity.UsersRecord;
import com.example.springwebtask.service.ICategoriesService;
import com.example.springwebtask.service.IProductsService;
import com.example.springwebtask.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.Timestamp;

import java.util.List;

@SpringBootApplication
public class SpringwebtaskApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringwebtaskApplication.class, args);

//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(timestamp);

        var context = SpringApplication.run(SpringwebtaskApplication.class, args);

//        IUserService userService = context.getBean(IUserService.class);
//        List<UserFind> a = userService.findUser("admin","admin");
//        System.out.println(a.get(0).getLoginId());
//        System.out.println(a.get(0).getPassword());
//        System.out.println(a.get(0).getName());

//        IProductsService productsService = context.getBean(IProductsService.class);
//        var list = productsService.findAll();
//        list.forEach(System.out::println);

//        ICategoriesService categoriesService = context.getBean(ICategoriesService.class);
//        var list = categoriesService.findAll();
//        list.forEach(System.out::println);

//        IProductsService productsService = context.getBean(IProductsService.class);
//        System.out.println("---------------------------------------------------------");
//        var list1 = productsService.searchProduct("キ");
//        list1.forEach(System.out::println);

//        IProductsService productsService = context.getBean(IProductsService.class);
//        ProductsFind productsFind = productsService.detailProduct("1");
//        System.out.println(productsFind);

//        IProductsService productsService = context.getBean(IProductsService.class);
//        int deletedNum = productsService.deleteProduct("222");
//        System.out.println(deletedNum);

//        IProductsService productsService = context.getBean(IProductsService.class);
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        ProductsRecord pr = new ProductsRecord(10,"2", 3,"second",98765,"","説明、mainから",timestamp,timestamp);
//        int upNum = productsService.updateProduct(pr);
//        System.out.println(upNum);

//        IProductsService productsService = context.getBean(IProductsService.class);
//        ProductsFind productsFind = productsService.detailProduct(12);
//        System.out.println(productsFind.getId());
//        System.out.println(productsFind.getProductId());
//        System.out.println(productsFind.getName());
//        System.out.println(productsFind.getCategoryName());
//        System.out.println(productsFind.getPrice());
//        System.out.println(productsFind.getDescription());

    }

}
