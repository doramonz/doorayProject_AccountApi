package com.nhnacademy.doorayProject.dto;

import com.nhnacademy.doorayProject.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDto {
    private String userId;
    private String password;
    private String userName;
    private String email;

    public User toEntity() {
        return new User(userId, password, userName, email, null);
    }
}
