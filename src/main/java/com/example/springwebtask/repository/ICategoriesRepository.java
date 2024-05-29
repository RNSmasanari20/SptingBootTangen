package com.example.springwebtask.repository;

import com.example.springwebtask.entity.CategoriesFind;

import java.sql.SQLException;
import java.util.List;

public interface ICategoriesRepository {

    List<CategoriesFind> findAll() throws SQLException;
}
