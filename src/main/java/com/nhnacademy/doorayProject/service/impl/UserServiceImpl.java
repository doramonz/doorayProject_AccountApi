package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.config.property.DefaultValue;
import com.nhnacademy.doorayProject.dto.*;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.exeption.UserDormancyException;
import com.nhnacademy.doorayProject.exeption.UserLoginFailException;
import com.nhnacademy.doorayProject.exeption.UserAlreadyExistException;
import com.nhnacademy.doorayProject.exeption.UserNotFoundException;
import com.nhnacademy.doorayProject.repository.UserRepository;
import com.nhnacademy.doorayProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final DefaultValue defaultValue;

    @Override
    public UserInfoDto getUserInfo(String userId) {
        return userRepository.getUserInfo(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void login(UserLoginDto userLoginDto) {
        if(!userRepository.existsById(userLoginDto.getUserId())) {
            throw new UserLoginFailException();
        }
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        if(userRepository.existsById(userRegisterDto.getUserId())) {
            throw new UserAlreadyExistException();
        }
        userRepository.save(userRegisterDto.toEntity());
    }

    @Override
    public void update(String userId, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userUpdateDto.update(user);
        userRepository.save(user);
    }

    @Override
    public void dormancy(String userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(user.getLatestLogin().isBefore(LocalDateTime.now().minusDays(defaultValue.getDormancyDay()))) {
            throw new UserDormancyException();
        }
    }

    @Override
    public List<UserInfoDto> getUserListIn(List<String> userIds) {
        return userRepository.getUserInfoListIn(userIds);
    }

    @Override
    public List<UserInfoDto> getUserList() {
        return userRepository.getUserInfoList();
    }

}
