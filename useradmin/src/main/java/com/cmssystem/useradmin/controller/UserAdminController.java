package com.cmssystem.useradmin.controller;


//import com.cmssystem.useradmin.dto.UserAdminAddResponseDto;
//import com.cmssystem.useradmin.dto.UserAdminDetailsDto;
//import com.cmssystem.useradmin.dto.UserAdminResponseDto;
//import com.cmssystem.useradmin.dto.UserDeleteResponseDto;

import com.cmssystem.useradmin.dto.*;
import com.cmssystem.useradmin.service.UserAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.cmssystem.useradmin.service.UserAdminService;

@Slf4j
@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/useradmin")
public class UserAdminController {


    @Autowired
    private UserAdminService userAdminService;

    @PostMapping(value = "/addUserAdmin")
    public ResponseEntity<?> addUser(@RequestBody UserAdminDetailsDto userAdminDetailsDto) {

        String response = "";
        UserAdminAddResponseDto userAdminAddResponseDto = new UserAdminAddResponseDto();

        log.warn("USERADMIN DETAIL:  {}",userAdminDetailsDto);


        if (userAdminDetailsDto.getName() == null || userAdminDetailsDto.getPassword() == null ||
                userAdminDetailsDto.getEmail() == null) {
            userAdminAddResponseDto.setAdded(false);
            userAdminAddResponseDto.setMessage("User Not Created");
        } else {
            userAdminAddResponseDto = userAdminService.addUser(userAdminDetailsDto);
            userAdminAddResponseDto.setAdded(true);
            userAdminAddResponseDto.setMessage("User Created");

        }

        return new ResponseEntity<>(userAdminAddResponseDto, HttpStatus.OK);
    }


    @GetMapping(value = "/searchUser")
    public ResponseEntity<?> searchUser(@RequestParam(value = "name") String name) {
        UserAdminResponseDto userAdminResponseDto = userAdminService.searchUser(name);
        return new ResponseEntity<>(userAdminResponseDto, HttpStatus.OK);
    }


    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(value = "pageNumber") int pageNumber, @RequestParam(value = "pageSize") int pageSize) {
        return ResponseEntity.ok(userAdminService.getAllUsers(pageNumber, pageSize));
    }


    @DeleteMapping(value = "/userDeleteByDetails")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "idDelete") String idDelete, @RequestParam(value = "id") String id) {
        UserDeleteResponseDto userDeleteResponseDto = userAdminService.deleteUser(idDelete, id);
        return new ResponseEntity<>(userDeleteResponseDto, HttpStatus.OK);
    }


    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> authenticateLoginUser(@RequestBody UserLoginDto userLoginDto) {

        boolean serviceResponse = true;
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();

        if (userLoginDto.getEmail() == null || userLoginDto.getPassword() == null || userLoginDto.getEmail().equals("") || userLoginDto.getPassword().equals("")) {
            userLoginResponseDto.setLogin(false);
            log.warn("Invalid Login");
            return new ResponseEntity<>(userLoginResponseDto, HttpStatus.BAD_REQUEST);
        }

        userLoginResponseDto = userAdminService.authenticateLoginUser(userLoginDto.getEmail(), userLoginDto.getPassword());

        return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
    }


  /*  @PostMapping(path = "/validateLogin")
    public ResponseEntity<?> validateLogin(@RequestBody LoginValidateDto loginValidateDTO){

        boolean serviceResponse = false;

        if(loginValidateDTO.getUserId()==null|| loginValidateDTO.getToken()==null || loginValidateDTO.getUserId().equals("") || loginValidateDTO.getToken().equals("")) {
            System.out.println("Not logged in");
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        }

        serviceResponse = userAdminService.validateLogin(loginValidateDTO.getUserId(),loginValidateDTO.getToken());

        log.warn("User is  Granted Login" + serviceResponse);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }*/

    @PostMapping(value = "/logout")
    public ResponseEntity<?> logout(@RequestBody LogOutDto logOutDto) {
        boolean response = userAdminService.logOut(logOutDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/getUserEmailId")
    public ResponseEntity<?> getUserEmailId(@RequestParam(value = "id") String id) {
        UserEmailDto userEmailDto = userAdminService.getUserEmailId(id);
        return new ResponseEntity<>(userEmailDto, HttpStatus.OK);
    }


}
