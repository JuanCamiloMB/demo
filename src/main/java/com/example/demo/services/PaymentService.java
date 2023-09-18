package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Payment;
import com.example.demo.repositories.CartDao;
import com.example.demo.repositories.PaymentDao;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PaymentService {
    private final PaymentDao paymentDao;
    private  final CartDao cartDao;

    @Autowired
    public PaymentService(PaymentDao paymentDao, CartDao cartDao){
        this.paymentDao = paymentDao;
        this.cartDao = cartDao;
    }

    public Payment createPayment(double amount, Cart cartInfo){
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());

        Long cartId = cartInfo.getId();
        Cart cart = cartDao.findById(cartId).orElse(null);


        if(cart != null){
            cart.setPayment(payment);
            payment.setCart(cart);
            cartDao.save(cart);
            return paymentDao.save(payment);
        } else {
            throw new EntityNotFoundException("Cart or Payment not found PAYMENT SERVICE");
        }


    }

}
