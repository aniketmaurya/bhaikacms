package com.coviam.metadata.response;

import com.coviam.metadata.entity.SingleVideo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleVideoResponse {
    private SingleVideo singleVideo;
    private Boolean isSuccessful;
}
