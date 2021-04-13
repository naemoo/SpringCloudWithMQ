package com.naemoo.userservice.dto;

import com.naemoo.userservice.vo.ResponseOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<ResponseOrder> orders;
}
