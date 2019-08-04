package com.coviam.metadata.dto.request;

import com.coviam.metadata.entity.Program;
import lombok.Data;

@Data
public class SingleVideoRequest {
    private String id;

    private Program programId;

    private String videoTitle;

    private String videoUrl;

    private String videoDescription;


    @Override
    public String toString() {
        return "SingleVideoRequest{" +
                "id='" + id + '\'' +
                ", programId=" + programId +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                '}';
    }
}
