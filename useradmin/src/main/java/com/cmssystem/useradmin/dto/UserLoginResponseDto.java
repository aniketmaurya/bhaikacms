package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto implements Serializable {
    private static final long serialVersionUID = -3317431634367507715L;
    private boolean isLogin;
    private String message;
    private UUID token;
    private String userId;
    private int roleId;

}
