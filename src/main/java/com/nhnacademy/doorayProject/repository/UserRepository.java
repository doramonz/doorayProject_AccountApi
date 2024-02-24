package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.dto.UserInfoDto;
import com.nhnacademy.doorayProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    @Query("SELECT new com.nhnacademy.doorayProject.dto.UserInfoDto(u.userName, u.password, u.email) FROM User u WHERE u.userId = :userId")
    Optional<UserInfoDto> getUserInfo(@Param("userId") String userId);

    @Query("SELECT new com.nhnacademy.doorayProject.dto.UserInfoDto(u.userName, u.password, u.email) FROM User u WHERE u.userId IN :userIds")
    List<UserInfoDto> getUserInfoListIn(String[] userIds);

    @Query("SELECT new com.nhnacademy.doorayProject.dto.UserInfoDto(u.userName, u.password, u.email) FROM User u")
    List<UserInfoDto> getUserInfoList();
}
