package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.*;

public interface UserService {
    UserInfoDto getUserInfo(String userId);

    void login(UserLoginDto userLoginDto);

    void register(UserRegisterDto userRegisterDto);

    void update(String userId, UserUpdateDto userUpdateDto);

    void dormancy(String userId);

    UserInfoDto[] getUserListIn(String[] userIds);

    UserInfoDto[] getUserList();
}
