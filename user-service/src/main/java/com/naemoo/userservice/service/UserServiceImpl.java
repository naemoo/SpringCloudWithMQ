package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.entity.UserEntity;
import com.naemoo.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);

        return null;
    }
}
