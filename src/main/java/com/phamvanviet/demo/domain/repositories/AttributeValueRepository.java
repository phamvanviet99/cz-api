package com.phamvanviet.demo.domain.repositories;

import com.phamvanviet.demo.domain.entities.category.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {
}
