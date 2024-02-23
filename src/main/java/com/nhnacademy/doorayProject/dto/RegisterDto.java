package com.nhnacademy.doorayProject.dto;

import com.nhnacademy.doorayProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String userId;
    private String password;
    private String userName;
    private String email;


    public RegisterDto(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
