package com.bhaikacms.scheduler.services.impl;

import com.bhaikacms.scheduler.dto.ProgramDto;
import com.bhaikacms.scheduler.dto.SendEmailDto;
import com.bhaikacms.scheduler.entity.Program;
import com.bhaikacms.scheduler.entity.User;
import com.bhaikacms.scheduler.repository.ProgramRepository;
import com.bhaikacms.scheduler.repository.UserRepository;
import com.bhaikacms.scheduler.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProgramRepository programRepository;

    @Override
    public boolean addUser(SendEmailDto sendEmailDto){
        User user=new User();
        user.setEmail(sendEmailDto.getEmail());
        user.setpId(sendEmailDto.getpId());
        user.setProgramName(sendEmailDto.getProgramName());
        user.setStartDate(sendEmailDto.getStartDate());
        user.setExpDate(sendEmailDto.getExpDate());
        boolean  result = userRepository.exists(sendEmailDto.getEmail());
        System.out.println("result : "+result);
        if(result){
            return false;
        }
        else{
            userRepository.save(user);
            return true;
        }

    }

    @Override
    public boolean addProgram(ProgramDto programDto){
        System.out.println(programDto.toString());
        Program program=new Program();
        program.setpId(programDto.getpId());
        program.setProgramName(programDto.getProgramName());
        program.setStartDate(programDto.getStartDate());
        program.setExpDate(programDto.getExpDate());
        boolean  result = userRepository.exists(programDto.getpId());
        System.out.println("result : "+result);
        if(result){
            return false;
        }
        else{
            programRepository.save(program);
            return true;
        }
    }
}
