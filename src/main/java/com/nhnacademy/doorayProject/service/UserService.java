package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.UserInfoDto;
import com.nhnacademy.doorayProject.dto.UserLoginDto;
import com.nhnacademy.doorayProject.dto.UserRegisterDto;
import com.nhnacademy.doorayProject.dto.UserUpdateDto;

public interface UserService {
    UserInfoDto getUserInfo(String userId);

    void login(UserLoginDto userLoginDto);

    void register(UserRegisterDto userRegisterDto);

    void update(String userId, UserUpdateDto userUpdateDto);

    void dormancy(String userId);
}
