package com.ecommerce.authuser.controllers;

import com.ecommerce.authuser.dtos.UserDto;
import com.ecommerce.authuser.models.User;
import com.ecommerce.authuser.services.interfaces.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        var newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(newUser));
    }
}
