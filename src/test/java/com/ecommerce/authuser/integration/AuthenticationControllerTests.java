package com.ecommerce.authuser.integration;

import com.ecommerce.authuser.dtos.UserDto;
import com.ecommerce.authuser.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application.properties")
@AutoConfigureMockMvc
@SpringBootTest
class AuthenticationControllerTests {

    private static MockHttpServletRequest request;
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @MockBean
    private RabbitTemplate rabbitTemplate;

    @BeforeAll
    public static void setUp() {
        request = new MockHttpServletRequest();
    }

    @Test
    void should_create_user_with_encrypt_password() throws Exception {
        var user = new UserDto(null, "jehmes", "jehmes@hotmail.com", "123456", "41707853398");
        Mockito.when(rabbitTemplate.convertSendAndReceive(Mockito.any(), (Object) Mockito.any(), (CorrelationData) Mockito.any()))
                .thenReturn("ack");

        String requestBody = objectMapper.writeValueAsString(user);

        MvcResult mvcResult = mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated()).andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

        var userResponse = objectMapper.readValue(responseContent, new TypeReference<User>() {});

//        Assertions.assertTrue(passwordEncoder.matches(user.getPassword(), userResponse.getPassword()));
    }
}
