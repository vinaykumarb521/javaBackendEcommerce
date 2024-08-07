package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.GstRate;
import com.ecommerce.model.Product;
import com.ecommerce.repositories.GstRateRepository;
import com.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GstRateRepository gstRateRepository;


    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public double getProductGstRate(String category) {
        return gstRateRepository.findByCategory(category)
                .map(GstRate::getPercentage)
                .orElse(0.0);
    }
}
