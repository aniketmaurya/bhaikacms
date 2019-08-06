package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String email;
    private String name;
    private int roleId;
    private boolean isActive;
    private Integer editable = 0;
}
