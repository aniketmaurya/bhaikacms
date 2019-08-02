package com.example.scheduler.service;

import com.example.scheduler.entity.SchedulingEntity;
import com.example.scheduler.repository.SchedulerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulingServiceImpl {
    @Autowired
    SchedulerRepo schedulerRepo1;

    public boolean addProg(SchedulingEntity test) {
        SchedulingEntity t1 = new SchedulingEntity();
        t1.setAlive(test.isAlive());
        t1.setExpiryDate(test.getExpiryDate());
        t1.setPid(test.getPid());
        t1.setStartDate(test.getStartDate());
        schedulerRepo1.save(t1);

        return true;
    }
}
