package com.ecommerce.RestEcommerce.services;

import com.ecommerce.RestEcommerce.entities.CartItem;
import com.ecommerce.RestEcommerce.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository repository;

    public CartItem create(Integer quantity){
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        repository.save(cartItem);
        return cartItem;
    }

    public List<CartItem> getAll(){
        List<CartItem> cartItems = repository.findAll();
        return cartItems;
    }
}
