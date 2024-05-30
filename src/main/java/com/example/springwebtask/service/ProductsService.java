package com.example.springwebtask.service;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;
import com.example.springwebtask.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductsService implements IProductsService{

    @Autowired
    private IProductsRepository productsRepository;
    @Override
    public List<ProductsFind> findAll(){
        try{
            return productsRepository.findAll();
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public int insert(ProductsRecord productsRecord){
        try {
            return productsRepository.insert(productsRecord);
        }catch (SQLException |  DuplicateKeyException e){
            return 0;
        }
    }

    @Override
    public List<ProductsFind> searchProduct(String str){
        try {
            return productsRepository.searchProduct(str);
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ProductsFind detailProduct(String str){
        try {
            return productsRepository.detailProduct(str);
        }catch (SQLException e){
            return null;
        }
    }

    @Override
    public ProductsFind detailProduct(int id){
        try {
            return productsRepository.detailProduct(id);
        }catch (SQLException | DuplicateKeyException e){
            return null;
        }
    }

    @Override
    public int deleteProduct(String str){
        try {
            return productsRepository.deleteProduct(str);
        }catch (SQLException e){
            return 0;
        }
    }

    @Override
    public int updateProduct(ProductsRecord productsRecord){
        try {
            return productsRepository.updateProduct(productsRecord);
        }catch (SQLException | DuplicateKeyException e){
            return 0;
        }
    }
}
