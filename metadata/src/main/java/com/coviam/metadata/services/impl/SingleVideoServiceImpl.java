package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.response.SingleVideoResponse;
import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SingleVideoRepository;
import com.coviam.metadata.services.SingleVideoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class SingleVideoServiceImpl implements SingleVideoServices {

    @Autowired
    private SingleVideoRepository singleVideoRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Override
    public SingleVideo addEpisodes(SingleVideo singleVideo) {
        log.info("Single video added videoName {}", singleVideo.getVideoTitle());
        return singleVideoRepository.save(singleVideo);
    }

    @Override
    public Boolean deleteEpisode(String videoId) {
        singleVideoRepository.deleteById(videoId);
        return Boolean.TRUE;
    }

    @Override
    public List<SingleVideo> getAllSingleVideo() {
        return singleVideoRepository.findAll();
    }

    @Override
    public List<SingleVideoResponse> addSingleVideoByBulkUpload(File csvFile) {
        String line = "";
        String csvSplitBy = ",";
        List<SingleVideoResponse> singleVideoResponseList = new ArrayList<>();
        try {
            FileReader file = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(file);
            line = br.readLine();
            String[] headers = line.split(csvSplitBy);

            if (headers[0].equalsIgnoreCase("Program Id") && headers[1].equalsIgnoreCase("video Title")
                    && headers[2].equalsIgnoreCase("video Url") && headers[3].equalsIgnoreCase("Video description")
                    && headers[4].equalsIgnoreCase("Thumbnail Image Url") && headers[5].equalsIgnoreCase("Avatar Image Url")
                    && headers[6].equalsIgnoreCase("crew list")) {

                while ((line = br.readLine()) != null) {
                    String[] records = line.split(csvSplitBy);
                    programRepository.findById(records[0]).ifPresent(program -> {

                        HashMap<String, String> images = new HashMap<>();
                        images.put("Thumbnail", records[4]);
                        images.put("Avatar", records[5]);

                        HashMap<String, String> crewMap = new HashMap<>();
                        String[] crewMapList = records[6].split(";");

                        for (String crew : crewMapList) {
                            String[] keyValue = crew.split(":");
                            crewMap.put(keyValue[0], keyValue[1]);
                        }

                        SingleVideo singleVideo = SingleVideo.builder()
                                .program(program)
                                .videoTitle(records[1])
                                .videoUrl(records[2])
                                .description(records[3])
                                .videoImgUrls(images)
                                .crewList(crewMap)
                                .build();

                        SingleVideo singleVideo1 = addEpisodes(singleVideo);
                        SingleVideoResponse singleVideoResponse = new SingleVideoResponse();
                        if (singleVideo1 == null) {
                            singleVideoResponse.setSingleVideo(singleVideo);
                            singleVideoResponse.setIsSuccessful(singleVideo1 != null);
                            singleVideoResponseList.add(singleVideoResponse);
                        }
                        log.info("Added single video with single video id:{}", singleVideo.getId());

                    });
                }
            }
        } catch (Exception e) {
            log.debug("Error while uploading season:{}", e.getMessage());
        }
        return singleVideoResponseList;
    }
}
