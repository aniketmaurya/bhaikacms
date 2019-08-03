package com.cmssystem.useradmin.service;

import com.cmssystem.useradmin.dto.*;
//import com.cmssystem.useradmin.dto.UserDeleteResponseDto;
import com.cmssystem.useradmin.entity.UserAdmin;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface UserAdminService {

    UserAdminAddResponseDto addUser(UserAdminDetailsDto userAdminDetailsDto);

    UserAdminResponseDto searchUser(String name);


    List<UserAdmin> getAllUsers(Integer pageNumber, Integer pageSize);

    UserDeleteResponseDto deleteUser(String idDelete,String id);

    UserLoginResponseDto authenticateLoginUser(String email, String password);

    //boolean validateLogin(String userId, UUID token);

    boolean logOut(LogOutDto logOutDto);

    UserEmailDto getUserEmailId(String id);
}
