package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.RegisterDto;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.repository.SignUpRepository;
import com.nhnacademy.doorayProject.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final SignUpRepository signUpRepository;


    @Override
    public RegisterDto createAccount(RegisterDto registerDto) {
        if(signUpRepository.findById(registerDto.getUserId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 있는 계정입니다");
        }
        User user = new User(
                registerDto.getUserId(),
                    registerDto.getPassword(),
                    registerDto.getUserName(),
                    registerDto.getEmail(),
                LocalDateTime.now());
        User newUser = signUpRepository.save(user);
        return new RegisterDto(newUser);
    }
}
