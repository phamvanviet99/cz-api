package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.RateDTO;
import com.phamvanviet.demo.domain.services.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rate")
public class RateController {
    @Autowired
    private RateService rateService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RateDTO rateDTO, @RequestHeader("accessToken") String accessToken) {
        return rateService.create(rateDTO, accessToken);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody RateDTO rateDTO, @RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return rateService.update(rateDTO, accessToken, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestHeader("accessToken") String accessToken, @PathVariable("id") Integer id) {
        return rateService.delete(accessToken, id);
    }
}
