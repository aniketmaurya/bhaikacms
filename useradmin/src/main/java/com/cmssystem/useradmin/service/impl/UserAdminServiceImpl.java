package com.cmssystem.useradmin.service.impl;

import com.cmssystem.useradmin.repository.UserAdminRepository;
import com.cmssystem.useradmin.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAdminServiceImpl implements UserAdminService {


    @Autowired
    private UserAdminRepository userAdminRepository;

}
