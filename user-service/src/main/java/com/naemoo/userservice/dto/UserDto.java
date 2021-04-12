package com.naemoo.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String name;
    private String password;
    private String userId;
    private LocalDateTime createdAt;

    private String encryptedPwd;
}
