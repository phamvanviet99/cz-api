package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.CategoryDTO;
import com.phamvanviet.demo.app.dtos.ProductDTO;
import com.phamvanviet.demo.app.responses.ProductResponse;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.services.CategoryService;
import com.phamvanviet.demo.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO, @RequestHeader ("accessToken") String accessToken) {
        return productService.create(productDTO ,accessToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ProductDTO productDTO, @RequestHeader ("accessToken") String accessToken, @PathVariable("id") Long id) {
        return productService.update(productDTO ,accessToken, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader ("accessToken") String accessToken, @PathVariable("id") Long id){
        return productService.delete(accessToken, id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@RequestHeader (value = "offset", required = false, defaultValue = "0") Integer offset, @RequestParam ("limit") Integer limit, @RequestParam ("accessToken") String accessToken, @PathVariable("id") Integer id){
        return productService.getProduct(offset, limit, accessToken, id);
    }
}
