package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.entity.UserEntity;
import com.naemoo.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    @Transactional
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd("password");
        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .userId(userDto.getUserId())
                .encryptedPwd(userDto.getEncryptedPwd())
                .name(userDto.getName())
                .build();
        UserEntity savedUser = userRepository.save(userEntity);
        UserDto retUserDto = modelMapper.map(savedUser, UserDto.class);
        return retUserDto;
    }
}
