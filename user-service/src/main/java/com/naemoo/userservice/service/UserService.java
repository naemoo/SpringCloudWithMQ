package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> findAllUsers();
}
