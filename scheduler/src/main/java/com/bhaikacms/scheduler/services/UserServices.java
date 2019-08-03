package com.bhaikacms.scheduler.services.impl;

import com.bhaikacms.scheduler.dto.SendEmailDto;

public interface UserServices {
    public boolean addUser(SendEmailDto userDto);
}
