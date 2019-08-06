package com.coviam.metadata.utility;

import com.coviam.metadata.dto.response.ProgramSearchResponse;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.entity.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class SearchUtility {

    @Async
    public Boolean callSearch(ProgramSearchResponse programSearchResponse) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://172.16.20.101:8080";

        Video video = restTemplate.postForObject(url + "solrSearch/addVideos", programSearchResponse, Video.class);
        log.info("Video Search Response: {}", video);
        return true;

    }

    @Async
    public void addToSearch(Program program) {

        ProgramSearchResponse programSearchResponse = new ProgramSearchResponse();
        BeanUtils.copyProperties(program, programSearchResponse);

        programSearchResponse.setKeywords(Arrays.asList(program.getKeywords().split(" ")));
        programSearchResponse.setLanguages(Arrays.asList(program.getLanguages().split(" ")));


        programSearchResponse.setCategory(program.getCategory());

        callSearch(programSearchResponse);
    }
}
