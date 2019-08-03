package com.bhaikacms.scheduler.services.impl;

import com.bhaikacms.scheduler.entity.User;
import com.bhaikacms.scheduler.repository.DeleteEmailNotificationRepository;
import com.bhaikacms.scheduler.services.DeleteEmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DeleteEmailNotificationImpl implements DeleteEmailNotification {

    @Autowired
    DeleteEmailNotificationRepository deleteEmailNotificationRepository;

    @Override
    public List<User> findAll() {

        List<User> user = (List<User>) deleteEmailNotificationRepository.findAll();
        System.out.println("**************"+user.size());

        System.out.println("======= : "+user.get(1).getEmail());
        System.out.println("======= : "+user.get(1).getpId());
        System.out.println("======= : "+user.get(1).getProgramName());
        System.out.println("======= : "+user.get(1).getExpDate());
        System.out.println("======= : "+user.get(1).getStartDate());

        System.out.println("======= : "+user.get(2).getEmail());
        System.out.println("======= : "+user.get(2).getpId());
        System.out.println("======= : "+user.get(2).getProgramName());
        System.out.println("======= : "+user.get(2).getExpDate());
        System.out.println("======= : "+user.get(2).getStartDate());

        System.out.println("======= : "+user.get(3).getEmail());
        System.out.println("======= : "+user.get(3).getpId());
        System.out.println("======= : "+user.get(3).getProgramName());
        System.out.println("======= : "+user.get(3).getExpDate());
        System.out.println("======= : "+user.get(3).getStartDate());


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        return user;
    }
    @Override
    public List<User> findByExpDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
        List<User> user = (List<User>) deleteEmailNotificationRepository.findByExpDate(timestamp);
        System.out.println("size  : "+user.size());
        System.out.println("****** : " + user.get(0).getEmail());
        System.out.println("****** : " + user.get(0).getpId());
        System.out.println("======= : " + user.get(0).getProgramName());
        System.out.println("======= : " + user.get(0).getExpDate());
        System.out.println("======= : " + user.get(0).getStartDate());
        return user;
    }


}
