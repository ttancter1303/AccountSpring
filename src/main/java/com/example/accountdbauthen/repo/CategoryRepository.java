package com.example.accountdbauthen.repo;

import com.example.accountdbauthen.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCategoryName(String categoryName);
}
