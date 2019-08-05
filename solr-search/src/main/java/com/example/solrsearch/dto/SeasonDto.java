package com.example.solrsearch.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeasonDto {


    String seasonId;
    String seasonNo;
    String seasonDescription;
    String seasonImg;
}
