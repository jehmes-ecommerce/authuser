package com.ecommerce.authuser.services;

import com.ecommerce.authuser.enums.ActionType;
import com.ecommerce.authuser.models.User;
import com.ecommerce.authuser.publishers.UserEventPublisher;
import com.ecommerce.authuser.repositories.UserRepository;
import com.ecommerce.authuser.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEventPublisher userEventPublisher;

    public UserServiceImpl(UserRepository userRepository, UserEventPublisher userEventPublisher) {
        this.userRepository = userRepository;
        this.userEventPublisher = userEventPublisher;
    }

    @Override
    @Transactional
    public User save(User newUser) {
        var user = userRepository.save(newUser);
        userEventPublisher.publishUserEvent(user.convertToUserEventDto(), ActionType.CREATE);
        return user;
    }
}
