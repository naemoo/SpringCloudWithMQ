package com.naemoo.userservice.controller;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.service.UserService;
import com.naemoo.userservice.vo.RequestUser;
import com.naemoo.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/user-service/users")
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

    @GetMapping
    public ResponseEntity<List<ResponseUser>> findAllUser() {
        List<UserDto> allUsers = userService.findAllUsers();
        List<ResponseUser> body = allUsers.stream().map(user -> modelMapper.map(user, ResponseUser.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUser> findUserById(@PathVariable("userId") String id) {
        UserDto userDto = userService.getUserById(id);
        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);
        return ResponseEntity.ok().body(responseUser);
    }

}
