package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdminAddResponseDto {
    boolean isAdded;
    String message;
    private String userId;

}
