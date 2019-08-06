package com.coviam.metadata.services;


import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.ProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SchedulingService {
    @Autowired
    private ProgramRepository programRepository;

    //for soft delete
    @Scheduled(fixedRate = 25000)
    public String expiryDetection() {
        log.debug("running expiryDetection");
        Long date = System.currentTimeMillis();

        List<Program>expired = programRepository.findByExpiryDateLessThan(date,  PageRequest.of(0,10)).getContent();
        List<Program> softDelete = new ArrayList<>();
        for (Program temp : expired) {
            temp.setIsAlive(false);
            softDelete.add(temp);
        }
        programRepository.saveAll(softDelete);

        Long  check = (LocalDate.now().minusDays(30)).toDate().getTime();
        List<Program> hardDelete = programRepository.findByExpiryDateLessThan(check,  PageRequest.of(0, 10)).getContent();
        programRepository.deleteAll(hardDelete);

        return "Successfully deleted";

    }

    //To schedule a program.
    @Scheduled(fixedRate = 25000)
    public String startProgram() {
        log.debug("Apoorv ka service start");
        long currentTime = System.currentTimeMillis();
        Page<Program> startTimeList = programRepository.findByStartDateLessThan(currentTime, new PageRequest(0, 10));
        for (Program temp : startTimeList) {
            temp.setIsAlive(false);
            if (temp.getExpiryDate() > temp.getStartDate()) {
                temp.setIsAlive(true);
            }
        }
        programRepository.saveAll(startTimeList);
        return "Program Started";
    }

}