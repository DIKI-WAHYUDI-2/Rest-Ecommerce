package com.ecommerce.RestEcommerce.services;

import com.ecommerce.RestEcommerce.entities.SubCategory;
import com.ecommerce.RestEcommerce.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SubCategoryService {

    @Autowired
    private SubCategoryRepository repository;

    public SubCategory create(SubCategory subCategory, MultipartFile file) throws IOException {
        SubCategory subCategoryNew = new SubCategory();
        subCategoryNew.setId(subCategory.getId());
        subCategoryNew.setName(subCategory.getName());
        subCategoryNew.setImage(file.getBytes());
        repository.save(subCategoryNew);
        return subCategoryNew;
    }

    public List<SubCategory> getAll(){
        return repository.findAll();
    }

    public List<SubCategory> getSubCategoriesByCategory(String name){
        return repository.getSubCategoriesByCategory(name);
    }
}
