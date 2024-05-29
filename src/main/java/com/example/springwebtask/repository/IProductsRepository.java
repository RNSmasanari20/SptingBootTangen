package com.example.springwebtask.repository;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;
import java.util.List;

public interface IProductsRepository {
    List<ProductsFind> findAll() throws SQLException;

    int insert(ProductsRecord productsRecord) throws SQLException , DuplicateKeyException;
}
