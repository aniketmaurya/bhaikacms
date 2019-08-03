package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto {
    private boolean isLogin;
    private String message;
    private UUID token;
    private String userId;
    private int roleId;

}
