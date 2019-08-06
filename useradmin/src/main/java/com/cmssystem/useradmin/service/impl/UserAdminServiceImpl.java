package com.cmssystem.useradmin.service.impl;

import com.cmssystem.useradmin.dto.*;
import com.cmssystem.useradmin.entity.UserAdmin;
import com.cmssystem.useradmin.entity.UserAdminToken;
import com.cmssystem.useradmin.repository.UserAdminRepository;
import com.cmssystem.useradmin.repository.UserAdminTokenRepository;
import com.cmssystem.useradmin.service.UserAdminService;
import com.cmssystem.useradmin.utility.AuditUtility;
import com.cmssystem.useradmin.utility.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        UserAdmin userAdmin1;
        log.warn(userAdminDetailsDto.toString());
        UserAdmin userAdmin = new UserAdmin();
        userAdmin.setEmail(userAdminDetailsDto.getEmail());
        userAdmin.setName(userAdminDetailsDto.getName());
        UtilityClass utilityClass = new UtilityClass();
        userAdmin.setPassword(utilityClass.hashPassword(userAdminDetailsDto.getPassword()));
        userAdmin.setActive(true);
        userAdmin.setRoleId(userAdminDetailsDto.getRoleId());
        // boolean result = userAdminRepository.findByEmail(userAdminDetailsDto.getEmail());
        boolean result = userAdminRepository.existsByEmail(userAdminDetailsDto.getEmail());
        log.debug("result : " + result);
        UserAdminAddResponseDto userAdminAddResponseDto = new UserAdminAddResponseDto();

        if (result) {
            userAdminAddResponseDto.setAdded(false);
            userAdminAddResponseDto.setMessage("User Already exists !!");
        } else {
            userAdmin1 = userAdminRepository.save(userAdmin);
            log.debug("userID:: {}", userAdmin1.getId());
            userAdminAddResponseDto.setAdded(true);
            userAdminAddResponseDto.setMessage("Created");
            userAdminAddResponseDto.setUserId(userAdmin.getId());

            AuditUtility auditUtility = new AuditUtility();
            auditUtility.addAudit(userAdmin1.getName(), userAdmin1.getEmail(), userAdmin1.getEmail(), new ArrayList<Change>());

        }
        return userAdminAddResponseDto;


    }

    @Override
    public Page<UserAdminResponseDto> searchUser(String input, Integer pageNumber, Integer pageSize) {

        Page<UserAdmin> pageNew = userAdminRepository
                .findByNameIgnoreCaseStartingWithOrEmailStartingWith(input, input, PageRequest.of(pageNumber, pageSize));
        return pageNew.map(this::convertToDtoNew);

    }

    private UserAdminResponseDto convertToDtoNew(UserAdmin userAdmin) {
        return UserAdminResponseDto.builder()
                .email(userAdmin.getEmail())
                .name(userAdmin.getName())
                .roleId(userAdmin.getRoleId())
                .isActive(userAdmin.isActive())
                .build();
    }

    @Override
    public Page<UserDto> getAllUsers(Integer pageNumber, Integer pageSize) {

        Page<UserAdmin> page = userAdminRepository.findAll(PageRequest.of(pageNumber, pageSize,
                Sort.by(Arrays.asList(new Sort.Order(Sort.Direction.ASC,"name"),
                        new Sort.Order(Sort.Direction.ASC,"isActive")))));
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
    public UserDeleteResponseDto deleteUser(String idDelete, String id) {
        UserAdmin userAdmin = userAdminRepository.findById(idDelete).get();
        UserAdmin userAdmin1 = userAdminRepository.findById(id).get();
        UserDeleteResponseDto userDeleteResponseDto = new UserDeleteResponseDto();
        if (userAdmin != null && (userAdmin1.getRoleId()) == 1) {
            userAdmin.setActive(!userAdmin.isActive());
            userDeleteResponseDto.setDeleted(true);
            userDeleteResponseDto.setMessage(userAdmin.getName() + " got disabled " + "by " + userAdmin1.getName());
            userAdminRepository.save(userAdmin);
            AuditUtility auditUtility = new AuditUtility();
            auditUtility.deleteAudit(userAdmin.getName(), userAdmin.getEmail(), userAdmin1.getEmail(), new ArrayList<Change>());

            return userDeleteResponseDto;
        } else {
            userAdmin.setActive(true);
            userDeleteResponseDto.setDeleted(false);
            userDeleteResponseDto.setMessage("Can't Delete");
            userAdminRepository.save(userAdmin);
            return userDeleteResponseDto;
        }

    }

    @CachePut(value = "token", key = "#email", cacheManager = "redisCacheManager")
    @Override
    public UserLoginResponseDto authenticateLoginUser(String email, String password) {
        UserAdmin userAdmin = userAdminRepository.findByEmail(email);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();

        long newtime = System.currentTimeMillis() + 6000000;

        if (userAdmin == null) {
            userLoginResponseDto.setLogin(false);
            userLoginResponseDto.setMessage("User Doesn't Exist");
            log.debug(userLoginResponseDto.getMessage());
            return userLoginResponseDto;
        }
        UtilityClass utilityClass = new UtilityClass();

        if (!utilityClass.checkPass(password, userAdmin.getPassword())) {
            userLoginResponseDto.setLogin(false);
            userLoginResponseDto.setMessage("Email or Password is wrong!");
            log.debug(userLoginResponseDto.getMessage());
            return userLoginResponseDto;
        }

        UserAdminToken userAdminToken = new UserAdminToken();
        userAdminToken.setId(userAdmin.getId());
        UUID uuid = UUID.randomUUID();
        userAdminToken.setToken(uuid);


        userAdminTokenRepository.save(userAdminToken);


        userLoginResponseDto.setLogin(true);
        userLoginResponseDto.setToken(uuid);
        userLoginResponseDto.setMessage("Logged in Successfully !!");
        userLoginResponseDto.setUserId(userAdmin.getId());
        userLoginResponseDto.setRoleId(userAdmin.getRoleId());
        userLoginResponseDto.setUserEmail(userAdmin.getEmail());
        userLoginResponseDto.setLoginTime(System.currentTimeMillis());
        log.debug("Time" + System.currentTimeMillis());
        log.debug(userLoginResponseDto.getMessage());

        log.debug("Before timer");
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        log.debug("Inside timer");
                        userAdminTokenRepository.deleteById(userAdmin.getId());
                        log.debug("timer executed");
                    }
                },
                7200000
        );
        log.debug("After timer exec");
        return userLoginResponseDto;

    }


    @Cacheable(value = "token", key = "#email", cacheManager = "redisCacheManager")
    public boolean validateLogin(String userId) {

        log.debug("UserId: " + userId);
        boolean checkUserLogin = userAdminRepository.existsById(userId);

        if (checkUserLogin) {
            log.debug("Token exist in the db.");
            checkUserLogin = true;
        } else {
            log.debug("No match for id");
            checkUserLogin = false;
        }

        return checkUserLogin;
    }

    @Override
    public Long countUser(Integer roleId) {
        Long countUser = userAdminRepository.countByRoleId(roleId);
        return countUser;
    }

    @Override
    public Boolean editChanges(EditDetailsDto editDetailsDto) {
        Change change = new Change();
        String fieldChanged;
        String oldValue;
        String newValue;
        Boolean response = userAdminRepository.existsById(editDetailsDto.getId());
        UserAdmin userAdmin = userAdminRepository.findById(editDetailsDto.getId()).get();
        List<Change> changes = new ArrayList<>();
        if (editDetailsDto.getName() != null) {
            fieldChanged = "Name";
            oldValue = userAdmin.getName();
            newValue = editDetailsDto.getName();
            userAdmin.setName(editDetailsDto.getName());
            changes.add(new Change(fieldChanged, oldValue, newValue));
            userAdminRepository.save(userAdmin);
        }
        if (editDetailsDto.getRoleId() != null) {
            fieldChanged = "RoleId";
            oldValue = Integer.toString(userAdmin.getRoleId());
            newValue = Integer.toString(editDetailsDto.getRoleId());
            userAdmin.setRoleId(editDetailsDto.getRoleId());
            changes.add(new Change(fieldChanged, oldValue, newValue));
            userAdminRepository.save(userAdmin);
        }
        if (editDetailsDto.getIsActive() != null) {
            fieldChanged = "Active Status";
            oldValue = Boolean.toString(userAdmin.isActive());
            newValue = Boolean.toString(editDetailsDto.getIsActive());
            changes.add(new Change(fieldChanged, oldValue, newValue));
            userAdmin.setActive(!userAdmin.isActive());
            userAdminRepository.save(userAdmin);
        }

        AuditUtility auditUtility = new AuditUtility();
        auditUtility.editAudit(userAdmin.getName(), userAdmin.getEmail(), userAdmin.getEmail(), changes);

        return response;
    }


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
        if (userAdmin != null) {
            userEmailDto.setEmail(userAdmin.getEmail());
            userEmailDto.setMessage("Email Sent");
        } else {
            userEmailDto.setMessage("No such user exists !!");
        }
        return userEmailDto;

    }

}