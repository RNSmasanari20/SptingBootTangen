package com.example.springwebtask.service;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;

import java.util.List;

public interface IProductsService {
    List<ProductsFind> findAll();

    int insert(ProductsRecord productsRecord);

    List<ProductsFind> searchProduct(String str);

    ProductsFind detailProduct(String str);

    ProductsFind detailProduct(int id);

    int deleteProduct(String str);

    int updateProduct(ProductsRecord productsRecord);
}
