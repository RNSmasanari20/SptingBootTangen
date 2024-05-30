package com.example.springwebtask.repository;

import com.example.springwebtask.entity.CategoriesFind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriesRepository implements ICategoriesRepository{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<CategoriesFind> findAll(){
        return jdbcTemplate.query("select * from categories",
                new DataClassRowMapper<>(CategoriesFind.class));
    }


}
