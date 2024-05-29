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

}
