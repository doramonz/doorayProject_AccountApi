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
public class UserUpdateDto {
    private String userName;
    private String password;
    private String email;

    public void update(User user) {
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
    }
}
