package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.entity.UserEntity;
import com.naemoo.userservice.repository.UserRepository;
import com.naemoo.userservice.vo.ResponseOrder;
import com.naemoo.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPwd(bCryptPasswordEncoder.encode(userDto.getPassword()));
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

    @Override
    public UserDto getUserById(String userId) {
        UserEntity findUser = userRepository.findByUserId(userId);
        if (findUser == null) {
            throw new RuntimeException("찾을 수 없는 사용자 입니다.");
        }
        // TODO: orders 구현
        List<ResponseOrder> orders = new ArrayList<>();
        UserDto userDto = modelMapper.map(findUser, UserDto.class);
        userDto.setOrders(orders);
        return userDto;
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map(ety -> modelMapper.map(ety, UserDto.class)).collect(Collectors.toList());
    }
}
