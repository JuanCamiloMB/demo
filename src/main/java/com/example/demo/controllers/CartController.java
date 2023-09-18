package com.example.demo.controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.Product;
import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @RequestBody Product product){
        Cart cart = cartService.getCartById(cartId);
        if (cart != null){
            cartService.addToCart(cart, product);
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long cartId){
        Cart cart = cartService.getCartById(cartId);
        if(cart != null){
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
