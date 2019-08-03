package com.example.scheduler.service;

import com.example.scheduler.entity.SchedulingEntity;
import com.example.scheduler.repository.SchedulerRepo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SchedulingService {
    @Autowired
    private SchedulerRepo schedulerRepo;

    //for soft delete
    @Scheduled(fixedRate = 1000)
    public String expiryDetection() {
        log.debug("running");
        long date = System.currentTimeMillis();
        List<SchedulingEntity> expired = null;
        expired = schedulerRepo.findByExpiryDateLessThan(date, new PageRequest(0, 100)).getContent();
        List<SchedulingEntity> softDelete = new ArrayList<>();
        for (SchedulingEntity temp : expired) {
            temp.setIsAlive(false);
            softDelete.add(temp);
        }
        schedulerRepo.save(softDelete);

            long check = (LocalDate.now().minusDays(30)).toDate().getTime();
        List<SchedulingEntity> hardDelete = schedulerRepo.findByExpiryDateLessThan(check, new PageRequest(0, 10)).getContent();
        schedulerRepo.delete(hardDelete);

        return "Successfully deleted";

    }

    //To schedule a program.
    @Scheduled(fixedRate = 5000)
    public String startProgram() {
        long currentTime = System.currentTimeMillis();
        Page<SchedulingEntity> startTimeList = schedulerRepo.findByStartDateLessThan(currentTime, new PageRequest(0, 10));
        for (SchedulingEntity temp : startTimeList) {
            if (temp.getExpiryDate() > temp.getStartDate()) {
                temp.setIsAlive(true);
            }
        }
        schedulerRepo.save(startTimeList);//todo save as a list
        return "Program Started";
    }
}



