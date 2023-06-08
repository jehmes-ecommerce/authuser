package com.ecommerce.authuser;

import com.ecommerce.authuser.exceptions.UserException;
import com.ecommerce.authuser.models.User;
import com.ecommerce.authuser.publishers.UserEventPublisher;
import com.ecommerce.authuser.repositories.UserRepository;
import com.ecommerce.authuser.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTests {

    @Autowired
    private UserService userService;
    @MockBean
    private UserEventPublisher userEventPublisher;
    @MockBean
    private UserRepository userRepository;

    User user = new User();

    @BeforeEach
    public void setUp() {
        user.setUserId(UUID.fromString("06cf7c16-0318-11ee-be56-0242ac120002"));
        user.setCpf("22186602296");
        user.setEmail("diogo-moraes89@distribuidorapetfarm.com.br");
        user.setUsername("diogo");
        user.setPassword("123456789");
    }

    @BeforeEach
    public void setUpEach() {

    }

    @Test
    void should_save_valid_user() throws UserException {
        when(userRepository.save(any())).thenReturn(user);
        var savedUser = userService.save(user);

        assertEquals(savedUser, user);
        verify(userRepository).save(any());
        verify(userEventPublisher, atLeast(1)).publishUserEvent(Mockito.any(), Mockito.any());
    }

//    @Test
//    void should_throws_exception_usernameAlreadyTaken() throws UserException {
//        when(userRepository.save(any())).thenReturn(user);
//        var savedUser = userService.save(user);
//
//        assertEquals(savedUser, user);
//        verify(userRepository).save(any());
//        verify(userEventPublisher, atLeast(1)).publishUserEvent(Mockito.any(), Mockito.any());
//    }

}
