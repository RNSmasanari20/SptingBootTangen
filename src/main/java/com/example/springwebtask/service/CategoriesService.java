package com.example.springwebtask.service;

import com.example.springwebtask.entity.CategoriesFind;
import com.example.springwebtask.repository.ICategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoriesService implements ICategoriesService{

    @Autowired
    ICategoriesRepository categoriesRepository;

    public List<CategoriesFind> findAll(){
        try {
            return categoriesRepository.findAll();
        }catch (SQLException e){
            return null;
        }
    }
}
