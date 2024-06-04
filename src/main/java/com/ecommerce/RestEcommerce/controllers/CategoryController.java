package com.ecommerce.RestEcommerce.controllers;

import com.ecommerce.RestEcommerce.entities.Category;
import com.ecommerce.RestEcommerce.response.ResponseHandler;
import com.ecommerce.RestEcommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        try{
            List<Category> categories = service.getAll();
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, categories);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


}
