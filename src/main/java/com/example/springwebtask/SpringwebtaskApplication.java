package com.example.springwebtask;

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
    }

}
