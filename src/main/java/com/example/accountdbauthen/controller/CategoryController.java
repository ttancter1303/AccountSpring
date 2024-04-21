package com.example.accountdbauthen.controller;

import com.example.accountdbauthen.entity.Category;
import com.example.accountdbauthen.entity.Product;
import com.example.accountdbauthen.repo.CategoryRepository;
import com.example.accountdbauthen.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryRepository CategoryRepository;
    @GetMapping()
    public ResponseEntity getAll(){
        List<Category> categories = CategoryRepository.findAll();
        return new ResponseEntity<>("get all "+categories, HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Category category){
        CategoryRepository.save(category);
        return new ResponseEntity<>("save category",HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity update(@RequestBody Category category, @PathVariable Integer id){
        Category category1 = CategoryRepository.findById(id).orElse(null);
        if (category == null){
            return new ResponseEntity<>("Category not found",HttpStatus.NOT_FOUND);
        }
        category1.setCategoryName(category.getCategoryName());
        category1.setUpdateAt(category.getUpdateAt());
        return new ResponseEntity<>("Update category ",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Category category = CategoryRepository.findById(id).orElse(null);
        if (category == null){
            return new ResponseEntity<>("category not found",HttpStatus.NOT_FOUND);
        }
        CategoryRepository.delete(category);
        return new ResponseEntity<>("Update category ",HttpStatus.OK);
    }
}
