package com.bhaikacms.scheduler.controller;

import com.bhaikacms.scheduler.dto.ProgramDto;
import com.bhaikacms.scheduler.dto.SendEmailDto;
import com.bhaikacms.scheduler.entity.User;
import com.bhaikacms.scheduler.services.DeleteEmailNotification;
import com.bhaikacms.scheduler.services.UserServices;
import com.bhaikacms.scheduler.services.impl.DeleteProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    UserServices userService;

    @Autowired
    DeleteEmailNotification deleteEmailNotification;

    @Autowired
    DeleteProgramServiceImpl deleteProgramService;

    @PostMapping(value = "/addUser")
    public ResponseEntity<?> addUser(@RequestBody SendEmailDto sendEmailDto){
        String response = "";
        boolean serviceResponse = true;

        System.out.println("sendEmailDto.getEmail() : "+sendEmailDto.getEmail());
        System.out.println("sendEmailDto.getProgramName() : "+sendEmailDto.getProgramName());
        System.out.println("sendEmailDto.getStartDate() : "+sendEmailDto.getStartDate());
        System.out.println("sendEmailDto.getExpDate() : "+sendEmailDto.getExpDate());
        System.out.println("sendEmailDto.getpId() : "+sendEmailDto.getpId());


        if(sendEmailDto.getEmail()==null||sendEmailDto.getProgramName()==null||sendEmailDto.getStartDate()==null||sendEmailDto.getExpDate()==null||sendEmailDto.getpId()==null) {
            System.out.println("=======1111=========");
            serviceResponse = false;
        }else {
            System.out.println("=======2222=========");
            serviceResponse = userService.addUser(sendEmailDto);
            System.out.println("=======33333=========");
        }
        System.out.println("=======4444========="+serviceResponse);
        if(serviceResponse){
            System.out.println("User created in Data base");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            System.out.println("User is not Created.");
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addProgram")
    public ResponseEntity<?> addProgram(@RequestBody ProgramDto programDto){
        String response = "";
        boolean serviceResponse = true;

        if(programDto.getpId()==null||programDto.getProgramName()==null||programDto.getStartDate()==null||programDto.getExpDate()==null)
            serviceResponse=false;
        else {
            serviceResponse = userService.addProgram(programDto);
        }

        if(serviceResponse){
            System.out.println("program created in Data base");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            System.out.println("program is not Created.");
            return new ResponseEntity<>( HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAllExpiryDetails")
    public ResponseEntity<?> getAllExpiryDetails(){
        boolean serviceResponse = true;
        deleteEmailNotification.findAll();
        //deleteEmailNotification.
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping(value = "/sendEmailNotification")
    public ResponseEntity<?> findByExpDate(){
        boolean serviceResponse = true;
        System.out.println("111111111111111111111111111111111111");
        deleteEmailNotification.findByExpDate();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping(value = "/deleteAndsendEmail")
    public ResponseEntity<?> deleteByExpDate(){
        boolean serviceResponse = true;
        System.out.println("111111111111111111111111111111111111");
        String message = deleteProgramService.deleteByExpDate();
        System.out.println("message  : "+message);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
