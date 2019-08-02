package com.example.scheduler.Controller;

import com.example.scheduler.entity.SchedulingEntity;
import com.example.scheduler.service.SchedulingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    SchedulingServiceImpl check;

    @PostMapping(value = "/add")
    public ResponseEntity<?> addProgram(@RequestBody SchedulingEntity schedulingEntity) {
        check.addProg(schedulingEntity);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
