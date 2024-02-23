package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.config.property.DefaultValue;
import com.nhnacademy.doorayProject.dto.UserInfoDto;
import com.nhnacademy.doorayProject.dto.UserLoginDto;
import com.nhnacademy.doorayProject.dto.UserRegisterDto;
import com.nhnacademy.doorayProject.dto.UserUpdateDto;
import com.nhnacademy.doorayProject.entity.User;
import com.nhnacademy.doorayProject.exeption.UserDormancyException;
import com.nhnacademy.doorayProject.exeption.UserLoginFailException;
import com.nhnacademy.doorayProject.exeption.UserAlreadyExistException;
import com.nhnacademy.doorayProject.exeption.UserNotFoundException;
import com.nhnacademy.doorayProject.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private DefaultValue defaultValue;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserInfo_notExistUser_userNotFoundException() {
        String userId = "NotFound";
        Mockito.when(userRepository.getUserInfo(userId)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->userService.getUserInfo(userId));
    }

    @Test
    void getUserInfo_existUser_user(){
        String userId = "Found";
        UserInfoDto user = new UserInfoDto("userName","password","email");
        Mockito.doReturn(Optional.of(user)).when(userRepository).getUserInfo(userId);
        UserInfoDto result = userService.getUserInfo(userId);
        Assertions.assertEquals(user,result);
    }

    @Test
    void login_notExistUser_LoginFailException() {
        UserLoginDto userLoginDto = new UserLoginDto("userId","password");
        Mockito.when(userRepository.existsById(userLoginDto.getUserId())).thenReturn(false);
        Assertions.assertThrows(UserLoginFailException.class,()->userService.login(userLoginDto));
    }

    @Test
    void login_existUser() {
        UserLoginDto userLoginDto = new UserLoginDto("userId","password");
        Mockito.when(userRepository.existsById(userLoginDto.getUserId())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->userService.login(userLoginDto));
    }

    @Test
    void register_existUserId_UserAlreadyExistException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("userId","userName","password","email");
        Mockito.when(userRepository.existsById(userRegisterDto.getUserId())).thenReturn(true);
        Assertions.assertThrows(UserAlreadyExistException.class,()->userService.register(userRegisterDto));
    }

    @Test
    void register_notExistUserId_user() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("userId","userName","password","email");
        Mockito.when(userRepository.existsById(userRegisterDto.getUserId())).thenReturn(false);
        Assertions.assertDoesNotThrow(()->userService.register(userRegisterDto));
    }

    @Test
    void update_notExistUser_UserNotFoundException() {
        String userId = "NotFound";
        UserUpdateDto userUpdateDto = new UserUpdateDto("userName","password","email");
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserNotFoundException.class,()->userService.update(userId,userUpdateDto));
    }

    @Test
    void update_existUser_user() {
        User user = new User("userId","userName","password","email", LocalDateTime.now());
        UserUpdateDto userUpdateDto = new UserUpdateDto("userName2","password2","email2");
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getUserId());
        Assertions.assertDoesNotThrow(()->userService.update(user.getUserId(),userUpdateDto));
    }

    @Test
    void dormancy_10daysAgo_access() {
        User user = new User("userId","userName","password","email", LocalDateTime.now().minusDays(10));
        Mockito.when(defaultValue.getDormancyDay()).thenReturn(365);
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getUserId());
        Assertions.assertDoesNotThrow(()->userService.dormancy(user.getUserId()));
    }

    @Test
    void dormancy_366daysAgo_denined() {
        User user = new User("userId","userName","password","email", LocalDateTime.now().minusDays(366));
        Mockito.when(defaultValue.getDormancyDay()).thenReturn(365);
        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(user.getUserId());
        Assertions.assertThrows(UserDormancyException.class,()->userService.dormancy(user.getUserId()));
    }
}