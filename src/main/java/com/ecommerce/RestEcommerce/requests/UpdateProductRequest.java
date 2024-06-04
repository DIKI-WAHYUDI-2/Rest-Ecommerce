package com.ecommerce.RestEcommerce.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UpdateProductRequest {

    private Integer id;
    private String name;
    private Integer price;
    private String brand;
    private String description;

}
