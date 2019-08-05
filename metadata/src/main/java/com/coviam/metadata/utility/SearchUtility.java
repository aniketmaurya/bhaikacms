package com.coviam.metadata.utility;

import com.coviam.metadata.dto.response.ProgramSearchResponse;
import com.coviam.metadata.entity.Program;
import org.springframework.beans.BeanUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class SearchUtility {

    public static Boolean callSearch(ProgramSearchResponse programSearchResponse) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://172.16.20.101:8080";

        Boolean response = restTemplate.postForObject(url + "solrSearch/addVideos", programSearchResponse, Boolean.class);

        return response;
    }

    public static void addToSearch(Program program) {

        ProgramSearchResponse programSearchResponse = new ProgramSearchResponse();
        BeanUtils.copyProperties(program, programSearchResponse);

        programSearchResponse.setKeywords(Arrays.asList(program.getKeywords().split(" ")));

        programSearchResponse.setCategory(program.getCategory());

        callSearch(programSearchResponse);
    }
}
