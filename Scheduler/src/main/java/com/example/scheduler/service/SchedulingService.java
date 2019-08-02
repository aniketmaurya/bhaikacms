package com.example.scheduler.service;

import com.example.scheduler.entity.SchedulingEntity;
import com.example.scheduler.repository.SchedulerRepo;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchedulingService {
    private static int i = 0;
    @Autowired
    SchedulerRepo test1;

    //for soft delete
    @Scheduled(fixedRate = 1000)
    public String expiryDetection() {
        System.out.println("running");
        long date = System.currentTimeMillis();
        List<SchedulingEntity> expired = null;
        expired = test1.findByExpiryDateList(date);
        for (SchedulingEntity temp : expired) {
            temp.setAlive(false);
            test1.save(temp);
        }

        //for hard Delete
        for (SchedulingEntity temp : expired) {
            long tempDate = temp.getExpiryDate();
            long check = (LocalDate.now().minusDays(30)).toDate().getTime();
            if (tempDate <= check) {
                test1.delete(temp);
            }


        }
        return "Successfully deleted";

    }

    //To schedule a program.
    @Scheduled(fixedRate = 5000)
    public String startProgram() {
        long currentTime = System.currentTimeMillis();
        List<SchedulingEntity> startTimeList = test1.findByStartDateList(currentTime);
        for (SchedulingEntity temp : startTimeList) {
            if (temp.getExpiryDate() > temp.getStartDate()) {
                temp.setAlive(true);
                test1.save(temp);
            }
        }
        return "Program Started";
    }
}



