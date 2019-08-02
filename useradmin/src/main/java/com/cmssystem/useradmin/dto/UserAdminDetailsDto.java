package com.cmssystem.useradmin.dto;

import lombok.Data;

@Data
public class UserAdminDetailsDto {

    private String name;
    private String password;
    private String email;
    private int roleId;

}
