package com.ecommerce.RestEcommerce.controllers;

import com.ecommerce.RestEcommerce.entities.Product;
import com.ecommerce.RestEcommerce.entities.SubCategory;
import com.ecommerce.RestEcommerce.requests.AddProductRequest;
import com.ecommerce.RestEcommerce.response.ResponseHandler;
import com.ecommerce.RestEcommerce.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
public class SubCategoryController {

    @Autowired
    private SubCategoryService service;

    @PostMapping()
    public ResponseEntity<Object> create(@RequestParam("id") Integer id,
                                         @RequestParam("name") String name,
                                         @RequestParam("image") MultipartFile file) {
        try {
            SubCategory request = new SubCategory();
            request.setId(id);
            request.setName(name);
            SubCategory subCategory = service.create(request,file);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, subCategory);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping("/category")
    public ResponseEntity<Object> getAll(@RequestParam("name") String name){
        try {
            List<SubCategory> subCategories = service.getSubCategoriesByCategory(name);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, subCategories);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
