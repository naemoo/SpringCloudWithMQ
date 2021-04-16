package com.naemoo.userservice.service;

import com.naemoo.userservice.dto.UserDto;
import com.naemoo.userservice.entity.UserEntity;
import com.naemoo.userservice.repository.UserRepository;
import com.naemoo.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity findUser = userRepository.findByEmail(email);
        if (findUser == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDto returnDto = modelMapper.map(findUser, UserDto.class);
        return returnDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("");
        }

        return new User(user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true, new ArrayList<>());
    }
}
