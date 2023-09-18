package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public float Total(){
        float total = 0;
        for (Product p : products){
            total += p.getPrice();
        }
        return total;
    }
    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
