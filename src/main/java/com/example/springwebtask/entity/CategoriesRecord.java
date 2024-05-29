package com.example.springwebtask.entity;

import java.sql.Timestamp;

public record CategoriesRecord(Integer id, String name,
                               Timestamp createTs, Timestamp updateTs) {}
