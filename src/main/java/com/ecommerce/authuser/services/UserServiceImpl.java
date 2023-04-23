package com.ecommerce.authuser.services;

import com.ecommerce.authuser.models.User;
import com.ecommerce.authuser.repositories.UserRepository;
import com.ecommerce.authuser.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
