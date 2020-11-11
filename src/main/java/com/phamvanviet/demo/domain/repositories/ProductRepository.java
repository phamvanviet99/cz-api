package com.phamvanviet.demo.domain.repositories;

import com.phamvanviet.demo.domain.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findProductByIdAndAndCreatedById(Long id, Integer userId);

    Page<Product> findProductByCategoryId(Integer categoryId, Pageable pageable);

    List<Product> findProductByCategoryId(Long categoryId);
}
