package com.example.springwebtask.repository;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;
import java.util.List;

public interface IProductsRepository {
    List<ProductsFind> findAll() throws SQLException;

    int insert(ProductsRecord productsRecord) throws SQLException , DuplicateKeyException;

    List<ProductsFind> searchProduct(String str) throws SQLException;

    ProductsFind detailProduct(String pId) throws SQLException;

    ProductsFind detailProduct(int id) throws SQLException,DuplicateKeyException;

    int deleteProduct(String deleteId) throws SQLException;



    int updateProduct(ProductsRecord productsRecord) throws SQLException,DuplicateKeyException;
}
