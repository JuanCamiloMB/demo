package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> getAllProducts(){
        return productDao.findAll();
    }

    public Product getProductById(Long id){
        return productDao.findById(id).orElse(null);
    }

    public void saveProduct(Product product){
        productDao.save(product);
    }

    public void deleteProduct(Long id){
        productDao.deleteById(id);
    }
}
