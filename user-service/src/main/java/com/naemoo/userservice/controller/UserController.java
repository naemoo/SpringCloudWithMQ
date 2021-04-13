package com.naemoo.userservice.controller;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.service.UserService;
import com.naemoo.userservice.vo.RequestUser;
import com.naemoo.userservice.vo.ResponseUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid RequestUser requestUser, Errors errors) {
        if (errors.hasErrors()) {
            ResponseEntity.badRequest();
        }
        UserDto userDto = modelMapper.map(requestUser, UserDto.class);
        UserDto retUser = userService.createUser(userDto);
        ResponseUser responseUser = modelMapper.map(retUser, ResponseUser.class);
        return ResponseEntity.created(linkTo(UserController.class).toUri()).body(responseUser);
    }
}
