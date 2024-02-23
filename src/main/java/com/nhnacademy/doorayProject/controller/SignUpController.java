package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.RegisterDto;
import com.nhnacademy.doorayProject.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class SignUpController {
    private SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/accounts/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterDto createAccount(@RequestBody RegisterDto registerDto){
        return signUpService.createAccount(registerDto);
    }
}
