package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.repositories.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartDao cartDao;

    @Autowired
    public CartService(CartDao cartDao){
        this.cartDao = cartDao;
    }

    public Cart getCartById(Long id){
        return cartDao.findById(id).orElse(null);
    }

    public Cart createCart(){
        return cartDao.save(new Cart());
    }

    public Cart addToCart(Cart cart, Product product){
        cart.getProducts().add(product);
        product.setCart(cart);
        return  cartDao.save(cart);
    }
}
