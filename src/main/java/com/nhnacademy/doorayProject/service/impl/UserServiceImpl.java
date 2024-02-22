package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.dto.UserInfoDto;
import com.nhnacademy.doorayProject.dto.UserLoginDto;
import com.nhnacademy.doorayProject.dto.UserRegisterDto;
import com.nhnacademy.doorayProject.dto.UserUpdateDto;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.exeption.DormancyUserException;
import com.nhnacademy.doorayProject.exeption.LoginFailException;
import com.nhnacademy.doorayProject.exeption.UserNotFoundException;
import com.nhnacademy.doorayProject.repository.UserRepository;
import com.nhnacademy.doorayProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserInfoDto getUserInfo(String userId) {
        return userRepository.getUserInfo(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void login(UserLoginDto userLoginDto) {
        if(!userRepository.existsById(userLoginDto.getUserId())) {
            throw new LoginFailException();
        }
    }

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        userRepository.save(userRegisterDto.toEntity());
    }

    @Override
    public void update(String userId, UserUpdateDto userUpdateDto) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        user.setUserName(userUpdateDto.getUserName());
        user.setPassword(userUpdateDto.getPassword());
        user.setEmail(userUpdateDto.getEmail());
        userRepository.save(user);
    }

    @Override
    public void dormancy(String userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException();
        }
        User user = userRepository.findById(userId).get();
        if(user.getLatestLogin().plusYears(1).isBefore(LocalDate.now().atStartOfDay())) {
            throw new DormancyUserException();
        }
    }

}
