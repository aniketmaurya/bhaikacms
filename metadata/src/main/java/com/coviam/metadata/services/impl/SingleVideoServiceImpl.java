package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SingleVideoRequest;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.repository.SingleVideoRepository;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.services.SingleVideoServices;
import com.coviam.metadata.utility.AuditUtility;
import com.coviam.metadata.utility.SearchUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SingleVideoServiceImpl implements SingleVideoServices {

    @Autowired
    ProgramServices programServices;
    @Autowired
    private SingleVideoRepository singleVideoRepository;
    @Autowired
    private SearchUtility searchUtility;
    @Autowired
    private AuditUtility auditUtility;

    @Override
    public SingleVideo addEpisodes(SingleVideoRequest singleVideoRequest) {
        log.info("Single video added videoName {}", singleVideoRequest.getVideoTitle());

        SingleVideo singleVideo = new SingleVideo();
        BeanUtils.copyProperties(singleVideoRequest, singleVideo);

        SingleVideo video = singleVideoRepository.save(singleVideo);
        Program program = programServices.getProgramById(video.getProgram().getId());
        video.setProgram(program);
        searchUtility.addSingleVideoToSearch(video);

        auditUtility.addAudit(
                video.getId(),
                "Single Video Prorgram",
                singleVideoRequest.getUserEmail(),
                new ArrayList<>());
        return video;
    }

    @Override
    public Boolean deleteEpisode(DeleteRequest deleteRequest) {

        singleVideoRepository.deleteById(deleteRequest.getId());
        auditUtility.deleteAudit(
                deleteRequest.getId(),
                "Single Video Prorgram",
                deleteRequest.getUserEmail(),
                new ArrayList<>());
        return Boolean.TRUE;
    }

    @Override
    public List<SingleVideo> getAllSingleVideo() {
        return singleVideoRepository.findAll();
    }
}
