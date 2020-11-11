package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.CategoryDTO;
import com.phamvanviet.demo.domain.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CategoryDTO categoryDTO, @RequestHeader("accessToken") String accessToken) {
        return categoryService.createCategory(categoryDTO, accessToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO, @RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return categoryService.update(categoryDTO, accessToken, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> update(@RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return categoryService.delete(accessToken, id);
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestHeader("accessToken") String accessToken) {
        return categoryService.getCategory(accessToken);
    }
}
