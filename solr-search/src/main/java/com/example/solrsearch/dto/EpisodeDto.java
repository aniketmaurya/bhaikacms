package com.example.solrsearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeDto {


    String episodeId;
    String episodeNumber;
    String episodeDescription;
    String episodeVideoUrl;


}
