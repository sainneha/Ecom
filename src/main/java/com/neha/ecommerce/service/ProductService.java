package com.neha.ecommerce.service;

import com.neha.ecommerce.model.Product;
import com.neha.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    // Get all products
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    // Get product by ID
    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Create a new product
    public Product create(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepo.save(product);
    }

    // Update existing product
    public Product update(Long id, Product updatedProduct) {
        Product existing = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStock(updatedProduct.getStock());
        existing.setUpdatedAt(LocalDateTime.now());

        return productRepo.save(existing);
    }

    // Delete product
    public void delete(Long id) {
        productRepo.deleteById(id);
    }
}
