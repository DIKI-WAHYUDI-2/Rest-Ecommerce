package com.ecommerce.RestEcommerce.requests;

import lombok.Data;

@Data
public class AddProductRequest {

    private String name;
    private Integer price;
    private String brand;
    private String description;
}
