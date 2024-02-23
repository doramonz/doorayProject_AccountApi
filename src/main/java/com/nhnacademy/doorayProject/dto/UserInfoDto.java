package com.nhnacademy.doorayProject.dto;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoDto {
    private String userName;
    private String password;
    private String email;
}
