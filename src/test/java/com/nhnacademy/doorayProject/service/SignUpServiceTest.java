package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.RegisterDto;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.repository.SignUpRepository;
import com.nhnacademy.doorayProject.service.impl.SignUpServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignUpServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private SignUpRepository signUpRepository;

    @InjectMocks
    private SignUpServiceImpl service;

    @Test
    void duplicateAccount(){
        RegisterDto registerDto = new RegisterDto();

        registerDto.setUserId("1234");
        registerDto.setPassword("1234");
        registerDto.setUserName("1234");
        registerDto.setEmail("ww@gmail.com");

        User user = new User("1111", "1234", "1234", "ww@gmail.com", LocalDateTime.now());

        given(signUpRepository.findById(registerDto.getUserId()))
                .willReturn(Optional.of(user));

        assertThrows(ResponseStatusException.class,()-> service.createAccount(registerDto));

    }

    @Test
    void createAccount(){
        RegisterDto registerDto = new RegisterDto();

        registerDto.setUserId("1234");
        registerDto.setPassword("1234");
        registerDto.setUserName("1234");
        registerDto.setEmail("ww@gmail.com");

        User user = new User("1111", "1234", "1234", "ww@gmail.com", LocalDateTime.now());

        given(signUpRepository.findById(registerDto.getUserId())).willReturn(Optional.empty());
        given(signUpRepository.save(any(User.class))).willReturn(user);
        service.createAccount(registerDto);

        verify(signUpRepository,times(1)).save(any(User.class));
    }
}
