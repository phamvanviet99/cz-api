package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.dtos.UserDTO;
import com.phamvanviet.demo.domain.services.AuthenService;
import com.phamvanviet.demo.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/authen")
public class AuthenController {
    @Autowired
    private UserService userSerivce;
    @Autowired
    private AuthenService authenService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO userDTO) {
        userSerivce.createUser(userDTO);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        return authenService.getTokenByUserNameAndPassword(userDTO.getUserName(), userDTO.getPassword());
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("accessToken") String accessToken) {
            return authenService.deleteTokenWhenLogout(accessToken);
    }


}
