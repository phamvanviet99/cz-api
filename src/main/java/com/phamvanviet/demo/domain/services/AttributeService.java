package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.AttributeDTO;
import com.phamvanviet.demo.domain.entities.category.Attribute;
import com.phamvanviet.demo.domain.entities.category.Category;
import com.phamvanviet.demo.domain.entities.product.Product;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.AttributeRepository;
import com.phamvanviet.demo.domain.repositories.CategoryRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AttributeService extends BaseService{
    @Autowired
    private AttributeRepository attributeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity create(AttributeDTO attributeDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());

        Category category = categoryRepository.getOne(attributeDTO.getCategoryId());
        if (Objects.isNull(category)) {
            throw new NotFoundException("Category Not Found!");
        }

        Attribute attribute = new Attribute();
        attribute.setName(attributeDTO.getName());
        attribute.setCategory(category);
        attribute.setCreatedBy(user);
        attributeRepository.save(attribute);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity update(AttributeDTO attributeDTO, String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());

        Category category = categoryRepository.getOne(attributeDTO.getCategoryId());
        if (Objects.isNull(category)) {
            throw new NotFoundException("Category Not Found!");
        }

        Attribute attribute = attributeRepository.getOne(id);
        attribute.setName(attributeDTO.getName());
        attribute.setCategory(category);
        attribute.setUpdatedBy(user);
        attributeRepository.save(attribute);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity delete(String accessToken, Integer id) {
        isAuthen(accessToken);
        Attribute attribute = attributeRepository.getOne(id);
        if (Objects.isNull(attribute))
            throw new NotFoundException("Attribute not found Id: " + id);
        attributeRepository.deleteById(id);
        return new ResponseEntity<>("deleted!", HttpStatus.OK);
    }


}
