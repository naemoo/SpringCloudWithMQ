package com.naemoo.userservice.controller;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.service.UserService;
import com.naemoo.userservice.vo.RequestUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity create(@RequestBody RequestUser requestUser) {
        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);
        return null;
    }


}
