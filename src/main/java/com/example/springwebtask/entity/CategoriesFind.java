package com.example.springwebtask.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoriesFind {
    private Integer id;
    private String name;
    private Timestamp createTs;
    private Timestamp updateTs;
}
