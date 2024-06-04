package com.ecommerce.RestEcommerce.controllers;

import com.ecommerce.RestEcommerce.entities.Category;
import com.ecommerce.RestEcommerce.entities.Product;
import com.ecommerce.RestEcommerce.requests.AddProductRequest;
import com.ecommerce.RestEcommerce.requests.UpdateProductRequest;
import com.ecommerce.RestEcommerce.response.ResponseHandler;
import com.ecommerce.RestEcommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping()
    public ResponseEntity<Object> create(@RequestParam("name") String name,
                                         @RequestParam("image") MultipartFile[] files,
                                         @RequestParam("price") Integer price,
                                         @RequestParam("brand") String brand,
                                         @RequestParam("description") String description,
                                         @RequestParam("category") Integer categoryId,
                                         @RequestParam("subcategory") Integer subcategoryId
    ) {
        try {
            AddProductRequest request = new AddProductRequest();
            request.setName(name);
            request.setPrice(price);
            request.setBrand(brand);
            request.setDescription(description);
            Product product = service.create(request,categoryId,subcategoryId, files);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, product);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping("/product")
    public ResponseEntity<Object> sendProductToCart(@RequestParam("id") Integer id){
        try {
            Product product = service.sendProductToCart(id);
            List<Product> products = Collections.singletonList(product);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK,products);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    @GetMapping("/product")
    public ResponseEntity<Object> getProductByName(@RequestParam("name") String productName){
        try{
            List<Product> products = service.getProductByName(productName);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, products);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/category-and-subcategory")
    public ResponseEntity<Object> getAllProductByCategory(@RequestParam("category") String category,
                                                          @RequestParam("subCategory") String subCategory){
        try{
            List<Product> products = service.getAllProductByCategoryAndSubCategory(category,subCategory);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, products);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/cart")
    public ResponseEntity<Object> getAllProductCartItem(){
        try{
            List<Product> products = service.getAllProductCartItem();
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, products);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        try{
            List<Product> products = service.getAll();
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, products);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/product-order-by")
    public ResponseEntity<Object> getAllOrderByNameDesc(){
        try{
            List<Product> products = service.getAllByOrderNameDesc();
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, products);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Integer id){
        try{
            Product product = service.get(id);
            return ResponseHandler.generateResponse("Successfully retrivied data!", HttpStatus.OK, product);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/product-by")
    public ResponseEntity<Object> update(@RequestParam("id") Integer id,
                                         @RequestParam("name") String name,
                                         @RequestParam("image") MultipartFile[] files,
                                         @RequestParam("price") Integer price,
                                         @RequestParam("brand") String brand,
                                         @RequestParam("description") String description,
                                         @RequestParam("category") Integer categoryId,
                                         @RequestParam("subcategory") Integer subcategoryId
    ){
        UpdateProductRequest request = new UpdateProductRequest();
        request.setName(name);
        request.setPrice(price);
        request.setBrand(brand);
        request.setDescription(description);
        try{
            Product product = service.updateById(id,request,categoryId,subcategoryId,files);
            return ResponseHandler.generateResponse("Successfully updated data!", HttpStatus.OK, product);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/product-by")
    public ResponseEntity<Object> delete(@RequestParam("id") Integer id){
        try {
            service.delete(id);
            return ResponseHandler.generateResponse("Successfully deleted data!", HttpStatus.OK, null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/product")
    public ResponseEntity<Object> removeProductFromCart(@RequestParam("id") Integer id){
        try {
            Product product = service.removeProductFromCart(id);
            List<Product> products = Collections.singletonList(product);
            return ResponseHandler.generateResponse("Successfully removed data!", HttpStatus.OK,null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
