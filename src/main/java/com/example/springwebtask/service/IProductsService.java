package com.example.springwebtask.service;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;

import java.util.List;

public interface IProductsService {
    List<ProductsFind> findAll();

    int insert(ProductsRecord productsRecord);
}
