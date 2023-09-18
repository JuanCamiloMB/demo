package com.example.demo.models;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private String name;
    private String price;

    private String img;
    private String description;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
