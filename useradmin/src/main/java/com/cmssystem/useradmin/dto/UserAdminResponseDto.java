package com.cmssystem.useradmin.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdminResponseDto {

    private String email;
    private String name;
    private String password;
    private int roleId;
    private boolean isActive;
}
