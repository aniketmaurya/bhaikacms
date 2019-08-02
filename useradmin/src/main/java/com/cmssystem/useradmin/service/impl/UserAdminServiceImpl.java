package com.cmssystem.useradmin.service.impl;

import com.cmssystem.useradmin.dto.*;
import com.cmssystem.useradmin.dto.UserAdminResponseDto;
import com.cmssystem.useradmin.dto.UserDeleteResponseDto;
import com.cmssystem.useradmin.entity.UserAdmin;
import com.cmssystem.useradmin.repository.UserAdminRepository;
import com.cmssystem.useradmin.service.UserAdminService;
import com.cmssystem.useradmin.utility.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class UserAdminServiceImpl implements UserAdminService {


    @Autowired
    private UserAdminRepository userAdminRepository;


    @Override
    public UserAdminAddResponseDto addUser(UserAdminDetailsDto userAdminDetailsDto) {

        System.out.println(userAdminDetailsDto.toString());
        UserAdmin userAdmin=new UserAdmin();
        userAdmin.setEmail(userAdminDetailsDto.getEmail());
        userAdmin.setName(userAdminDetailsDto.getName());
        UtilityClass utilityClass = new UtilityClass();
        userAdmin.setPassword(utilityClass.hashPassword(userAdminDetailsDto.getPassword()));
        userAdmin.setRoleId(userAdminDetailsDto.getRoleId());
        userAdmin.setActive(true);

        boolean result = userAdminRepository.exists(userAdminDetailsDto.getEmail());
        System.out.println("result : "+result);
        UserAdminAddResponseDto userAdminAddResponseDto = new UserAdminAddResponseDto();
        if(result){
            userAdminAddResponseDto.setAdded(false);
            userAdminAddResponseDto.setMessage("Not Created");
        }
        else {
            userAdminRepository.save(userAdmin);
            userAdminAddResponseDto.setAdded(true);
            userAdminAddResponseDto.setMessage("Created");
        }

        return userAdminAddResponseDto;
    }

    @Override
    public UserAdminResponseDto searchUser(String name) {

        UserAdmin userAdmin = userAdminRepository.findByName(name);

        UserAdminResponseDto userAdminResponseDto = new UserAdminResponseDto();
        userAdminResponseDto.setEmail(userAdmin.getEmail());
        userAdminResponseDto.setName(name);
        userAdminResponseDto.setRoleId(userAdmin.getRoleId());
        userAdminResponseDto.setActive(userAdmin.isActive());
        System.out.println(userAdminResponseDto.toString());
        //System.out.println("Gave details for the " + name);

        return userAdminResponseDto;


    }

   @Override
    public List<UserAdmin> getAllUsers(Integer pageNumber,Integer pageSize) {
       Page<UserAdmin> page=userAdminRepository.findAll(new PageRequest(pageNumber,pageSize));
       return page.getContent();
    }

    @Override
    public UserDeleteResponseDto deleteUser(String id) {
        UserAdmin userAdmin = userAdminRepository.findOne(id);
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto();
        if(userAdmin != null)
        {
            userAdmin.setActive(false);
            userDeleteResponseDto.setDeleted(true);
        }
        else
        {
            userDeleteResponseDto.setDeleted(false);
        }
        return userDeleteResponseDto;
    }

    @Override
    public UserLoginResponseDto authenticateLoginUser(String email, String password) {
        UserAdmin userAdmin = userAdminRepository.findByEmail(email);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();


        if(userAdmin==null){
            userLoginResponseDto.setLogin(false);
            userLoginResponseDto.setMessage("User Doesn't Exist");
            System.out.println("User is not logged In");
            return userLoginResponseDto;
        }
        UtilityClass utilityClass = new UtilityClass();
        if(!utilityClass.checkPass(password,userAdmin.getPassword())){
            userLoginResponseDto.setLogin(false);
            userLoginResponseDto.setMessage("Email or Password is wrong!");
            log.debug(userLoginResponseDto.getMessage());
            return userLoginResponseDto;
        }
        userLoginResponseDto.setLogin(true);
        userLoginResponseDto.setMessage("User is logged in");
        log.debug(userLoginResponseDto.getMessage());
        return userLoginResponseDto;

    }


}
