package com.example.springwebtask.entity;

import java.sql.Timestamp;

public record ProductsRecord(Integer id, String pId, Integer cId,
                             String name, Integer price, String images,
                             String description, Timestamp createTs, Timestamp updateTs) {
}
