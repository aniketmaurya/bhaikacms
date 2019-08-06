package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuditRequestDto {


    private String asset; //(name of user/admin whose fields were changed)
    private String assetId;//(email of user/admin whose fields were changed)
    private String action; //(ADDED/DELETED/MODIFIED)
    private String modifier; //(email id of user/admin who performed the change)
    private List<Change> changes; //(Changes=>fieldChanged, oldValue, newValue)
    private Integer flag = 1;

}
