package com.coviam.metadata.dto.response;

import com.coviam.metadata.entity.Episode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeResponse {
    private List<? extends Episode> episode;
}
