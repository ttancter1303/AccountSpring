package com.example.accountdbauthen.repo;

import com.example.accountdbauthen.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Integer> {
    Product findByProductName(String productName);
}
