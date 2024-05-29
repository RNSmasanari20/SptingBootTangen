package com.example.springwebtask.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProductsFind {

    private Integer id;
    @NotEmpty(message = "商品IDは必須です")
    private String productId;
    private String categoryName;
    @NotEmpty(message = "商品名は必須です")
    private String name;
    @NotNull(message = "単価は必須です")
    private Integer price;
    private String imagePath;
    private String description;
    private Timestamp createTs;
    private Timestamp updateTs;

}
