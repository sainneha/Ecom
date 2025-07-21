// --- repository/ProductRepository.java ---
package com.neha.ecommerce.repository;

import com.neha.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
