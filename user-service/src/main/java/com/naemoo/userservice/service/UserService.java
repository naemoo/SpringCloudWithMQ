package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
