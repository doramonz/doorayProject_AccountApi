package com.nhnacademy.doorayProject.service;

import com.nhnacademy.doorayProject.dto.*;

import java.util.List;

public interface UserService {
    UserInfoDto getUserInfo(String userId);

    void login(UserLoginDto userLoginDto);

    void register(UserRegisterDto userRegisterDto);

    void update(String userId, UserUpdateDto userUpdateDto);

    void dormancy(String userId);

    List<UserInfoDto> getUserListIn(List<String> userIdList);

    List<UserInfoDto> getUserList();
}
