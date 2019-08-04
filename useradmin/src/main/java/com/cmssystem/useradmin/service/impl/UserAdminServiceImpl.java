package com.cmssystem.useradmin.service.impl;

import com.cmssystem.useradmin.dto.*;
import com.cmssystem.useradmin.entity.UserAdmin;
import com.cmssystem.useradmin.entity.UserAdminToken;
import com.cmssystem.useradmin.repository.UserAdminRepository;
import com.cmssystem.useradmin.repository.UserAdminTokenRepository;
import com.cmssystem.useradmin.service.UserAdminService;
import com.cmssystem.useradmin.utility.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

//import com.cmssystem.useradmin.dto.UserAdminResponseDto;
//import com.cmssystem.useradmin.dto.UserDeleteResponseDto;

@Slf4j
@Service
public class UserAdminServiceImpl implements UserAdminService {


    @Autowired
    private UserAdminRepository userAdminRepository;

    @Autowired
    private UserAdminTokenRepository userAdminTokenRepository;

    @Override
    public UserAdminAddResponseDto addUser(UserAdminDetailsDto userAdminDetailsDto) {

        log.warn(userAdminDetailsDto.toString());
        UserAdmin userAdmin=new UserAdmin();
        userAdmin.setEmail(userAdminDetailsDto.getEmail());
        userAdmin.setName(userAdminDetailsDto.getName());
        UtilityClass utilityClass = new UtilityClass();
        userAdmin.setPassword(utilityClass.hashPassword(userAdminDetailsDto.getPassword()));
        userAdmin.setActive(true);
        userAdmin.setRoleId(userAdminDetailsDto.getRoleId());
        // boolean result = userAdminRepository.findByEmail(userAdminDetailsDto.getEmail());
        boolean result = userAdminRepository.existsByEmail(userAdminDetailsDto.getEmail());
        log.warn("result : "+result);
        UserAdminAddResponseDto userAdminAddResponseDto = new UserAdminAddResponseDto();
        if(result){
            userAdminAddResponseDto.setAdded(false);
            userAdminAddResponseDto.setMessage("User Already exists !!");
        }
        else {
            userAdminRepository.save(userAdmin);
            userAdminAddResponseDto.setAdded(true);
            userAdminAddResponseDto.setMessage("Created");
            userAdminAddResponseDto.setUserId(userAdmin.getId());
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
        log.warn("details: {}",userAdminResponseDto.toString());
        log.warn("Gave details for {}",name);

        return userAdminResponseDto;


    }

   @Override
   public Page<UserDto> getAllUsers(Integer pageNumber, Integer pageSize) {
       Page<UserAdmin> page = userAdminRepository.findAll(PageRequest.of(pageNumber, pageSize));
       return page.map(this::convertToDto);
   }

    private UserDto convertToDto(UserAdmin userAdmin) {
        return UserDto.builder()
                .id(userAdmin.getId())
                .email(userAdmin.getEmail())
                .isActive(userAdmin.isActive())
                .name(userAdmin.getName())
                .roleId(userAdmin.getRoleId())
                .build();
    }

    @Override
    public UserDeleteResponseDto deleteUser(String idDelete,String id) {
        UserAdmin userAdmin = userAdminRepository.findById(idDelete).get();
        UserAdmin userAdmin1 = userAdminRepository.findById(id).get();
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto();
        if (userAdmin != null && (userAdmin1.getRoleId()) == 1 && (userAdmin.isActive() != false))
        {
            userAdmin.setActive(false);
            userDeleteResponseDto.setDeleted(true);
            userAdminRepository.save(userAdmin);
            return userDeleteResponseDto;
        }
        else
        {
            userAdmin.setActive(true);
            userDeleteResponseDto.setDeleted(false);
            userAdminRepository.save(userAdmin);
            return userDeleteResponseDto;
        }

    }

    @Cacheable(value = "token", key = "#email", cacheManager = "redisCacheManager")
    @Override
    public UserLoginResponseDto authenticateLoginUser(String email, String password) {
        UserAdmin userAdmin = userAdminRepository.findByEmail(email);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();


        if(userAdmin==null){
            userLoginResponseDto.setLogin(false);
            userLoginResponseDto.setMessage("User Doesn't Exist");
            log.warn(userLoginResponseDto.getMessage());
            return userLoginResponseDto;
        }
        UtilityClass utilityClass = new UtilityClass();

        if(!utilityClass.checkPass(password,userAdmin.getPassword())){
            userLoginResponseDto.setLogin(false);
            userLoginResponseDto.setMessage("Email or Password is wrong!");
            log.debug(userLoginResponseDto.getMessage());
            return userLoginResponseDto;
        }

        UserAdminToken userAdminToken= new UserAdminToken();
        userAdminToken.setId(userAdmin.getId());
        UUID uuid = UUID.randomUUID();
        userAdminToken.setToken(uuid);


        userAdminTokenRepository.save(userAdminToken);


        userLoginResponseDto.setLogin(true);
        userLoginResponseDto.setToken(uuid);
        userLoginResponseDto.setMessage("Logged in Successfully !!");
        userLoginResponseDto.setUserId(userAdmin.getId());
        userLoginResponseDto.setRoleId(userAdmin.getRoleId());
        log.warn(userLoginResponseDto.getMessage());
        return userLoginResponseDto;

    }


   /* @Override
    public boolean validateLogin(String userId, UUID token) {

        log.warn("UserId: " + userId + "Token: " + token);
        boolean checkUserLogin = userAdminTokenRepository.exists(token);

        if(checkUserLogin){
            log.warn("Token exist in the db.");
            UserAdminToken userAdminToken = userAdminTokenRepository.findOne(token);
            System.out.println("User Email: " + userAdminToken.getUserAdmin().getEmail());
            if(userAdminToken.getUserAdmin().getId().equals(userId)) {
                System.out.println("Email id and token match");
                //email id and token match
                checkUserLogin =  true;
            }
            else {
                log.warn("Email id and token didn't match");
                checkUserLogin =  false;
            }
        }

        return checkUserLogin;
    }*/

    @Override
    public boolean logOut(LogOutDto logOutDto) {
        userAdminTokenRepository.deleteById(logOutDto.getUserId());
        log.warn("Token Deleted For user.");
        return true;
    }

    @Override
    public UserEmailDto getUserEmailId(String id) {
        UserAdmin userAdmin = userAdminRepository.findById(id).get();
        UserEmailDto userEmailDto = new UserEmailDto();
        if(userAdmin!=null) {
            userEmailDto.setEmail(userAdmin.getEmail());
            userEmailDto.setMessage("Email Sent");
        }
        else {
            userEmailDto.setMessage("No such user exists !!");
        }
        return userEmailDto;

    }

}
