package com.nhnacademy.doorayProject.dto;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserInfoDto {
    private String userId;
    private String userName;
    private String email;
}
