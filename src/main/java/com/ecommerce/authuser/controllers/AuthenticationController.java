package com.ecommerce.authuser.controllers;

import com.ecommerce.authuser.dtos.UserDto;
import com.ecommerce.authuser.enums.UserType;
import com.ecommerce.authuser.exceptions.UserException;
import com.ecommerce.authuser.models.User;
import com.ecommerce.authuser.services.interfaces.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) throws UserException {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
        newUser.setUserType(UserType.USER);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(newUser));
    }

    @PostMapping("/signup/adm/usr")
    public ResponseEntity<User> registerAdmUser(@RequestBody UserDto userDto) throws UserException {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);
        newUser.setUserType(UserType.ADMIN);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(newUser));
    }
}
