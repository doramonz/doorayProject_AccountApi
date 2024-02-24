package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.RegisterDto;

public interface SignUpService {
    RegisterDto createAccount(RegisterDto registerDto);
}
