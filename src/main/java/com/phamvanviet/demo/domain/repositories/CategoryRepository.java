package com.phamvanviet.demo.domain.repositories;

import com.phamvanviet.demo.domain.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoryByIdNotNull();
    Category findCategoryById(Integer id);
}
