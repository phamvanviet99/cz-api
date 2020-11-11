package com.phamvanviet.demo.app.controllers;

import com.phamvanviet.demo.app.responses.UserResponse;
import com.phamvanviet.demo.app.dtos.UserDTO;
import com.phamvanviet.demo.domain.services.AuthenService;
import com.phamvanviet.demo.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userSerivce;
    @Autowired
    private AuthenService authenService;

    @GetMapping
    public UserResponse findUser(@RequestHeader("accessToken") String accessToken) {
        UserResponse users = userSerivce.getUser(accessToken);
        return users;
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO userDTO) {
        return userSerivce.createUser(userDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO userDTO, @RequestHeader("accessToken") String accessToken) {
        return userSerivce.updateUser(userDTO, accessToken);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestHeader("accessToken") String accessToken) {
        return userSerivce.deleteUser(accessToken);
    }

//    @DeleteMapping
//    public void delete(@RequestBody long[] ids) {
//        userSerivce.delete(ids);
//    }

    @PutMapping(value = "/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid UserDTO user, @RequestHeader("accessToken") String token) {
        String newPassword = user.getPassword();
        return authenService.changePassword(newPassword, token);
    }

}
