package com.nhnacademy.doorayProject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserInfoListDto {
    private List<UserInfoDto> userInfoList;
}
