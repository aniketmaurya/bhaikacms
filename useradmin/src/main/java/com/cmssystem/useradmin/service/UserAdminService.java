package com.cmssystem.useradmin.service;

import com.cmssystem.useradmin.dto.*;
import org.springframework.data.domain.Page;

//import com.cmssystem.useradmin.dto.UserDeleteResponseDto;

public interface UserAdminService {

    UserAdminAddResponseDto addUser(UserAdminDetailsDto userAdminDetailsDto);


    Page<UserAdminResponseDto> searchUser(String input, Integer pageNumber, Integer pageSize);


    Page<UserDto> getAllUsers(Integer pageNumber, Integer pageSize);

    UserDeleteResponseDto deleteUser(String idDelete, String id);

    UserLoginResponseDto authenticateLoginUser(String email, String password);

    //boolean validateLogin(String userId, UUID token);

    boolean logOut(LogOutDto logOutDto);

    UserEmailDto getUserEmailId(String id);

    boolean validateLogin(String userId);

    Long countUser(Integer roleId);

    Boolean editChanges(EditDetailsDto editDetailsDto);
}
