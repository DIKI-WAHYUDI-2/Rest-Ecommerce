package com.ecommerce.RestEcommerce.services;

import com.ecommerce.RestEcommerce.entities.Category;
import com.ecommerce.RestEcommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> getAll(){
        List<Category> category = repository.findAll();
        return category;
    }
}
