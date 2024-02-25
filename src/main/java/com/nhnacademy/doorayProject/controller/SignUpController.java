package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.RegisterDto;
import com.nhnacademy.doorayProject.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {
    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

//    @PostMapping("/accounts/register")
//    public RegisterDto createAccount(@RequestBody RegisterDto registerDto){
//        return signUpService.createAccount(registerDto);
//    }
}
