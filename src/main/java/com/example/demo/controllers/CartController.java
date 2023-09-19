package com.example.demo.controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.Payment;
import com.example.demo.models.Product;
import com.example.demo.services.CartService;
import com.example.demo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final PaymentService paymentService;

    @Autowired
    public CartController(CartService cartService, PaymentService paymentService){
        this.cartService = cartService;
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> createCart(){
        Cart created = cartService.createCart();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{cartId}/add")
    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @RequestBody Product product){
        Cart cart = cartService.getCartById(cartId);
        if (cart != null){
            Cart saved = cartService.addToCart(cart, product);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{cartId}/products")
    public ResponseEntity<List<Product>> getCartProducts(@PathVariable Long cartId){
        Cart cart = cartService.getCartById(cartId);
        if(cart != null){
            return ResponseEntity.ok(cart.getProducts());
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

    @PostMapping("/{cartId}/pay")
    public  ResponseEntity<Payment> payCart(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        float total = cart.Total();

        Payment payment = paymentService.createPayment(total, cart);
        return ResponseEntity.ok(payment);
    }
}
