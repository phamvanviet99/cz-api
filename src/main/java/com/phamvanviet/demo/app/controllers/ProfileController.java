package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.ProfileDTO;
import com.phamvanviet.demo.domain.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody @Valid ProfileDTO profileDTO, @RequestHeader("accessToken") String accessToken) {
            return profileService.updateProfile(profileDTO, accessToken);


    }

}
