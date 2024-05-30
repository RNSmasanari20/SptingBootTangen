package com.example.springwebtask.repository;

import com.example.springwebtask.entity.ProductsFind;
import com.example.springwebtask.entity.ProductsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepository implements IProductsRepository{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<ProductsFind> findAll(){
        return jdbcTemplate.query("select products.id, product_id, categories.name as categoryName, products.name, price, image_path, description, products.created_at, products.updated_at from products " +
                        "inner join categories " +
                        "on products.category_id = categories.id " +
                        "order by products.id asc;",
                new DataClassRowMapper<>(ProductsFind.class));
    }

    @Override
    public int insert(ProductsRecord productsRecord){
        var param = new MapSqlParameterSource();
        param.addValue("product_id",productsRecord.pId());
        param.addValue("category_id",productsRecord.cId());
        param.addValue("name",productsRecord.name());
        param.addValue("price",productsRecord.price());
        param.addValue("description", productsRecord.description());
        param.addValue("created_at",productsRecord.createTs());
        return jdbcTemplate.update("insert into products " +
                "(product_id,category_id,name,price,image_path,description, created_at) " +
                "values (:product_id, :category_id, :name, :price, null, :description, :created_at)",param);
    }


    @Override
    public List<ProductsFind> searchProduct(String str){
        String send = "%" + str + "%";
        var param = new MapSqlParameterSource();
        param.addValue("search", send);
        return jdbcTemplate.query("select products.id, product_id, categories.name as categoryName, products.name, price, image_path, description, products.created_at, products.updated_at from products " +
                        "inner join categories " +
                        "on products.category_id = categories.id " +
                        "where products.name like :search " +
                        "order by products.id asc;"
                ,param, new DataClassRowMapper<>(ProductsFind.class));
    }

    @Override
    public ProductsFind detailProduct(String pId){
        var param = new MapSqlParameterSource();
        param.addValue("pId", pId);
        var list = jdbcTemplate.query("select products.id, product_id, categories.name as categoryName, products.name, price, image_path, description, products.created_at, products.updated_at from products " +
                        "inner join categories " +
                        "on products.category_id = categories.id " +
                        "where product_id = :pId;",
                param, new DataClassRowMapper<>(ProductsFind.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public ProductsFind detailProduct(int id){
        var param = new MapSqlParameterSource();
        param.addValue("pId", id);
        var list = jdbcTemplate.query("select products.id, product_id, categories.name as categoryName, products.name, price, image_path, description, products.created_at, products.updated_at from products " +
                        "inner join categories " +
                        "on products.category_id = categories.id " +
                        "where products.id = :pId;",
                param, new DataClassRowMapper<>(ProductsFind.class));
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public int deleteProduct(String deleteId){
        var param = new MapSqlParameterSource();
        param.addValue("pId", deleteId);
        return jdbcTemplate.update("DELETE FROM products WHERE product_id = :pId", param);
    }

    @Override
    public int updateProduct(ProductsRecord productsRecord){
        var param = new MapSqlParameterSource();
        param.addValue("pId",productsRecord.pId());
        param.addValue("cId", productsRecord.cId());
        param.addValue("name", productsRecord.name());
        param.addValue("price", productsRecord.price());
        param.addValue("description",productsRecord.description());
        param.addValue("updated_at", productsRecord.createTs());
        param.addValue("Id", productsRecord.id());
        return jdbcTemplate.update("update products " +
                "set product_id = :pId, " +
                "category_id = :cId, " +
                "name = :name, " +
                "price = :price, " +
                "description = :description, " +
                "updated_at = :updated_at " +
                "where id = :Id;",param);
    }

}
