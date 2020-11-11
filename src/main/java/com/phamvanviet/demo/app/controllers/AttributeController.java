package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.AttributeDTO;
import com.phamvanviet.demo.domain.services.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/attribute")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AttributeDTO attributeDTO, @RequestHeader("accessToken") String accessToken) {
        return attributeService.create(attributeDTO, accessToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AttributeDTO attributeDTO, @RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return attributeService.update(attributeDTO, accessToken, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader ("accessToken") String accessToken, @PathVariable("id") Integer id){
        return attributeService.delete(accessToken, id);
    }

}
