package com.ecommerce.authuser.services;

import com.ecommerce.authuser.enums.ActionType;
import com.ecommerce.authuser.enums.UserExceptionMessage;
import com.ecommerce.authuser.exceptions.UserException;
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
    public User save(User newUser) throws UserException {
        var checkUsername = userRepository.findByUsername(newUser.getUsername());
        var checkEmail = userRepository.findByEmail(newUser.getEmail());
        if (checkUsername.isPresent()) {
            throw new UserException(UserExceptionMessage.USERNAME_ALREADY_TAKEN.getMessage());
        }
        if (checkEmail.isPresent()) {
            throw new UserException(UserExceptionMessage.EMAIL_ALREADY_TAKEN.getMessage());
        }
        var user = userRepository.save(newUser);
        userEventPublisher.publishUserEvent(user.convertToUserEventDto(), ActionType.CREATE);
        return user;
    }
}
