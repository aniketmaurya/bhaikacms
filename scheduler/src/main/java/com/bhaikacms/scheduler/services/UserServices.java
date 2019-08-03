package com.bhaikacms.scheduler.services;

import com.bhaikacms.scheduler.dto.ProgramDto;
import com.bhaikacms.scheduler.dto.SendEmailDto;

public interface UserServices {
    public boolean addUser(SendEmailDto sendEmailDto);
    public boolean addProgram(ProgramDto programDto);
}
