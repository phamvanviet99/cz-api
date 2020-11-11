package com.phamvanviet.demo.domain.services;

import com.phamvanviet.demo.app.dtos.AttributeDTO;
import com.phamvanviet.demo.app.dtos.AttributeValueDTO;
import com.phamvanviet.demo.domain.entities.category.Attribute;
import com.phamvanviet.demo.domain.entities.category.AttributeValue;
import com.phamvanviet.demo.domain.entities.category.Category;
import com.phamvanviet.demo.domain.entities.user.User;
import com.phamvanviet.demo.domain.exception.NotFoundException;
import com.phamvanviet.demo.domain.model.TokenInfo;
import com.phamvanviet.demo.domain.repositories.AttributeRepository;
import com.phamvanviet.demo.domain.repositories.AttributeValueRepository;
import com.phamvanviet.demo.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AttributeValueService extends BaseService{
    @Autowired
    private AttributeValueRepository attributeValueRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    public ResponseEntity create(AttributeValueDTO attributeValueDTO, String accessToken) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());

        Attribute attribute = attributeRepository.getOne(attributeValueDTO.getAttributeId());
        if (Objects.isNull(attribute)) {
            throw new NotFoundException("Attribute Not Found!");
        }

        AttributeValue attributeValue = new AttributeValue();
        attributeValue.setValue(attributeValueDTO.getValue());
        attributeValue.setAttribute(attribute);
        attributeValue.setCreatedBy(user);
        attributeValueRepository.save(attributeValue);
        return new ResponseEntity<>("created!", HttpStatus.OK);
    }

    public ResponseEntity update(AttributeValueDTO attributeValueDTO, String accessToken, Integer id) {
        TokenInfo tokenInfo = isAuthen(accessToken);
        User user = userRepository.getOne(tokenInfo.getUserId());

        Attribute attribute = attributeRepository.getOne(attributeValueDTO.getAttributeId());
        if (Objects.isNull(attribute)) {
            throw new NotFoundException("Attribute Not Found!");
        }

        AttributeValue attributeValue = attributeValueRepository.getOne(id);
        attributeValue.setValue(attributeValueDTO.getValue());
        attributeValue.setAttribute(attribute);
        attributeValue.setUpdatedBy(user);
        attributeValueRepository.save(attributeValue);
        return new ResponseEntity<>("updated!", HttpStatus.OK);
    }

    public ResponseEntity delete(String accessToken, Integer id) {
        isAuthen(accessToken);
        AttributeValue attributeValue = attributeValueRepository.getOne(id);
        if (Objects.isNull(attributeValue))
            throw new NotFoundException("Attribute value not found Id: " + id);
        attributeValueRepository.deleteById(id);
        return new ResponseEntity<>("deleted!", HttpStatus.OK);
    }

}
