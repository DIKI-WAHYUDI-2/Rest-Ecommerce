package com.ecommerce.RestEcommerce.repositories;

import com.ecommerce.RestEcommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p JOIN p.category c JOIN p.subCategory sc WHERE c.name = :category AND sc.name = :subCategory")
    List<Product> getAllProductByCategoryAndSubCategory(@Param("category") String category,@Param("subCategory") String subCategory);

    @Query("SELECT p FROM Product p JOIN p.image pi WHERE p.name = :name")
    List<Product> getProductByName(@Param("name") String name);

    @Query("SELECT p FROM  Product  p JOIN p.cartItem ci")
    List<Product> getAllProductCartItem();

    @Query("SELECT p FROM Product p ORDER BY p.name ASC")
    List<Product> getAllOrderByDesc();
}
