package com.ecommerce.RestEcommerce.requests;

import lombok.Data;


@Data
public class UpdateUserRequest {

    private String email;
    private String name;
    private String password;
}
