package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    List<UserDto> findAllUsers();

    UserDto getUserDetailsByEmail(String email);
}
