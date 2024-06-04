package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
}
