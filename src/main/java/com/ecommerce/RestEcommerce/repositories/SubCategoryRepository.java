package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Integer> {


    @Query("SELECT sc FROM SubCategory sc JOIN sc.category c WHERE c.name = :name ")
    List<SubCategory> getSubCategoriesByCategory(@RequestParam("name")String name);
}
