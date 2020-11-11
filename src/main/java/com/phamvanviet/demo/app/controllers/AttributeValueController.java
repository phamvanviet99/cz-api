package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.AttributeDTO;
import com.phamvanviet.demo.app.dtos.AttributeValueDTO;
import com.phamvanviet.demo.domain.services.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/attribute_value")
public class AttributeValueController {
    @Autowired
    private AttributeValueService attributeValueService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AttributeValueDTO attributeValueDTO, @RequestHeader("accessToken") String accessToken) {
        return attributeValueService.create(attributeValueDTO, accessToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AttributeValueDTO attributeValueDTO, @RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return attributeValueService.update(attributeValueDTO, accessToken, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader ("accessToken") String accessToken, @PathVariable("id") Integer id){
        return attributeValueService.delete(accessToken, id);
    }

}
