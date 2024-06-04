package com.ecommerce.RestEcommerce.services;

import com.ecommerce.RestEcommerce.entities.*;
import com.ecommerce.RestEcommerce.repositories.*;
import com.ecommerce.RestEcommerce.requests.AddProductRequest;
import com.ecommerce.RestEcommerce.requests.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;

    public Product create(AddProductRequest request, Integer categoryId, Integer subCategoryId, MultipartFile[] files) throws IOException {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("category not found"));
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId).orElseThrow(()-> new RuntimeException("subcategory not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setSubCategory(subCategory);

        List<ProductImage> images = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                byte[] imageData = file.getBytes();
                ProductImage productImage = new ProductImage();
                productImage.setImage(imageData);
                images.add(productImage);
            }
        }

        product.setImage(images);
        repository.save(product);

        return product;
    }

    public Product updateById(Integer id, UpdateProductRequest request,Integer categoryId,Integer subcategoryId,
                              MultipartFile[] files) throws IOException{

        Category category = categoryRepository.findById(categoryId).orElseThrow();
        SubCategory subCategory = subCategoryRepository.findById(subcategoryId).orElseThrow();
        Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setSubCategory(subCategory);

        List<ProductImage> images = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                byte[] imageData = file.getBytes();
                ProductImage productImage = new ProductImage();
                productImage.setImage(imageData);
                images.add(productImage);
            }
        }
        product.setImage(images);
        repository.save(product);
        return product;
    }
//    public Product create(AddProductRequest request, MultipartFile[] files) throws IOException {
//        Product product = new Product();
//        product.setName(request.getName());
//        product.setPrice(request.getPrice());
//        product.setBrand(request.getBrand());
//        product.setDescription(request.getDescription());
//
//        List<ProductImage> images = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//            if (file != null && !file.isEmpty()) {
//                byte[] imageData = file.getBytes();
//                ProductImage productImage = new ProductImage();
//                productImage.setImage(imageData);
//                images.add(productImage);
//            }
//        }
//
//        product.setImage(images);
//        repository.save(product);
//
//        return product;
//    }

    public List<Product> getProductByName(String productName){
        List<Product> products = repository.getProductByName(productName);
        return products;
    }

    public List<Product> getAllProductByCategoryAndSubCategory(String categoryName,String subCategoryName){
        List<Product> products = repository.getAllProductByCategoryAndSubCategory(categoryName,subCategoryName);
        return products;
    }

    public List<Product> getAll(){
        List<Product> product = repository.findAll();
        return product;
    }

    public List<Product> getAllByOrderNameDesc(){
        List<Product> products = repository.getAllOrderByDesc();
        return products;
    }

    public Product get(Integer id){
        Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        return product;
    }

    public void delete(Integer id){
        Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        repository.delete(product);
    }

    public Product sendProductToCart(Integer id){
        Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));

        if(product.getCartItem() != null){
            throw new RuntimeException("Product is already in the cart");
        }

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItemRepository.save(cartItem);
        product.setCartItem(cartItem);
        repository.save(product);
        return product;
    }
    
    public Product removeProductFromCart(Integer id){
        Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        if(product.getCartItem() == null){
            throw new RuntimeException("Product is not in the cart");
        }
        CartItem cartItem = product.getCartItem();

        product.setCartItem(null);
        repository.save(product);

        cartItemRepository.delete(cartItem);

        return product;
    }

    public List<Product> getAllProductCartItem(){
        List<Product> products = repository.getAllProductCartItem();
        return products;
    }

}
