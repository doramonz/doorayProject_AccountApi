package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void getUserInfo() {
        userService.getUserInfo("userId");
    }
}