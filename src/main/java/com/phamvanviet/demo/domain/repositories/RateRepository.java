package com.phamvanviet.demo.domain.repositories;

import com.phamvanviet.demo.domain.entities.rate.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
    Rate findRateByIdAndCreatedById(Integer rateId, Integer userId);
}
