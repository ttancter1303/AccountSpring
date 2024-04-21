package com.example.accountdbauthen.controller;

import com.example.accountdbauthen.entity.Account;
import com.example.accountdbauthen.entity.Product;
import com.example.accountdbauthen.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping()
    public ResponseEntity getAll(){
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>("get all "+products, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Product product){
        productRepository.save(product);
        return new ResponseEntity<>("save product",HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity update(@RequestBody Product product, @PathVariable Integer id){
        Product product1 = productRepository.findById(id).orElse(null);
        if (product == null){
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
        product1.setUpdateAt(product.getUpdateAt());
        product1.setProductName(product.getProductName());
        product1.setPrice(product.getPrice());
        return new ResponseEntity<>("Update product ",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Product product1 = productRepository.findById(id).orElse(null);
        if (product1 == null){
            return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }
        productRepository.delete(product1);
        return new ResponseEntity<>("Update product ",HttpStatus.OK);
    }
}
