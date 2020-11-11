package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.RateDTO;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.rate.Rate;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.ProductRepository;
import com.phamvanviet.demo.domain.repositories.RateRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RateService extends BaseService{
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity create(RateDTO rateDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Product product = productRepository.getOne(rateDTO.getProductId());
        if (Objects.isNull(product))
            throw new NotFoundException("Product not found Id: "+rateDTO.getProductId());

        User user = userRepository.getOne(tokenInfo.getUserId());
        Rate rate = new Rate();
        rate.setRatePoint(rateDTO.getRatePoint());
        rate.setContent(rateDTO.getContent());
        rate.setProduct(product);
        rate.setCreatedBy(user);
        rateRepository.save(rate);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity update(RateDTO rateDTO, String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Rate rate = rateRepository.findRateByIdAndCreatedById(id,tokenInfo.getUserId());
        if (Objects.isNull(rate))
            throw new NotFoundException("Rate not found Id: "+id);
        User user = userRepository.getOne(tokenInfo.getUserId());

        rate.setRatePoint(rateDTO.getRatePoint());
        rate.setContent(rateDTO.getContent());
        rate.setUpdatedBy(user);
        rateRepository.save(rate);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity delete(String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        Rate rate = rateRepository.findRateByIdAndCreatedById(id,tokenInfo.getUserId());
        if (Objects.isNull(rate))
            throw new NotFoundException("Rate not found Id: "+id);
        rateRepository.deleteById(id);
        return new ResponseEntity<>("deleted!", HttpStatus.OK);
    }

}
