package com.coviam.metadata.utility;

import com.coviam.metadata.dto.response.EpisodeResponse;
import com.coviam.metadata.dto.response.SingleVideoResponse;
import com.coviam.metadata.entity.SingleVideo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class SearchUtility {

    RestTemplate restTemplate = new RestTemplate();

    String url = "http://172.16.20.101:8080";


    @Async
    public void addEpisodesToSearch(EpisodeResponse episodeResponse) {
        EpisodeResponse episodeResponse1= restTemplate.postForObject(url + "solrSearch/addEpisodesToSearch", episodeResponse, EpisodeResponse.class);
        log.debug("Video Search Response: {}", episodeResponse1);
    }

    @Async
    public void addSingleVideoToSearch(SingleVideo singleVideo) {
        SingleVideo singleVideo1= restTemplate.postForObject(url + "solrSearch/addSingleVideoToSearch", singleVideo, SingleVideo.class);
        log.debug("Video Search Response: {}", singleVideo1);
    }
}
