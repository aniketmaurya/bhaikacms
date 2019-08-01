package com.coviam.metadata.dto;

import com.coviam.metadata.entity.Program;

public class SingleVideoDto {
    String id;

    Program programId;

    String videoURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
