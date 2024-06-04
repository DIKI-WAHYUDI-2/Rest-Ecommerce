package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
