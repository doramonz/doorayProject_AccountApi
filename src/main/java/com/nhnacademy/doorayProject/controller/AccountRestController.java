package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.UserInfoDto;
import com.nhnacademy.doorayProject.dto.UserLoginDto;
import com.nhnacademy.doorayProject.dto.UserRegisterDto;
import com.nhnacademy.doorayProject.dto.UserUpdateDto;
import com.nhnacademy.doorayProject.exeption.UserNotFoundException;
import com.nhnacademy.doorayProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/accounts")
@RestController
public class AccountRestController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable("userId") String userId) {
        ResponseEntity<UserInfoDto> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.getUserInfo(userId), HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof UserNotFoundException) {
                responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginDto userLoginDto) {
        ResponseEntity<Void> responseEntity;
        try {
            userService.login(userLoginDto);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterDto> register(@RequestBody UserRegisterDto userRegisterDto) {
        ResponseEntity<UserRegisterDto> responseEntity;
        try {
            userService.register(userRegisterDto);
            responseEntity = new ResponseEntity<>(userRegisterDto, HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("/{userId}/update")
    public ResponseEntity<UserUpdateDto> update(@PathVariable("userId") String userId, @RequestBody UserUpdateDto userUpdateDto) {
        ResponseEntity<UserUpdateDto> responseEntity;
        try {
            userService.update(userId, userUpdateDto);
            responseEntity = new ResponseEntity<>(userUpdateDto, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/{userId}/dormancy")
    public ResponseEntity<Void> dormancy(@PathVariable("userId") String userId) {
        ResponseEntity<Void> responseEntity;
        try {
            userService.dormancy(userId);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/list")
    public ResponseEntity<UserInfoDto[]> getUserListIn(@RequestBody String[] userIds) {
        ResponseEntity<UserInfoDto[]> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(userService.getUserListIn(userIds), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
