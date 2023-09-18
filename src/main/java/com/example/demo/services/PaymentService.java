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

    public Payment createPayment(double amount, Cart cart){
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentDate(new Date());

        cart.setPayment(payment);
        payment.setCart(cart);
        try {
            Payment savedPayment = paymentDao.save(payment);
            cartDao.save(cart);
            return savedPayment;
        } catch (Exception e){
            System.out.println("Can't do that");
            return new Payment();
        }
    }
}
