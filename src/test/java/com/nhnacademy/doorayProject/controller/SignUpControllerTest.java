package com.nhnacademy.doorayProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.doorayProject.dto.RegisterDto;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.service.SignUpService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import  org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignUpControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SignUpService signUpService;

    @Test
    void setSignUpService() throws Exception {
        RegisterDto registerDto = new RegisterDto();

        registerDto.setUserId("1234");
        registerDto.setPassword("1234");
        registerDto.setUserName("1234");
        registerDto.setEmail("ww@gmail.com");

        User user = new User("1234", "1234", "1234", "ww@gmail.com", LocalDateTime.now());

        ObjectMapper objectMapper = new ObjectMapper();

        given(signUpService.createAccount(any(RegisterDto.class)))
                .willReturn(registerDto);
        mockMvc.perform(MockMvcRequestBuilders.post("/accounts/register")
                .content(objectMapper.writeValueAsString(registerDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId",equalTo("1234")))
                .andExpect(jsonPath("$.password",equalTo("1234")))
                .andExpect(jsonPath("$.userName",equalTo("1234")))
                .andExpect(jsonPath("$.email",equalTo("ww@gmail.com")));
    }
}
