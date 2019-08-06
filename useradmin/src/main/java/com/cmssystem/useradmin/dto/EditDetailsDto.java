package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditDetailsDto {
    private String id;
    private String name;
    private Integer roleId;
    private Boolean isActive;
}
