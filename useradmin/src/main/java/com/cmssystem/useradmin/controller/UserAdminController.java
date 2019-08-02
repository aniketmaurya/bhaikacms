package com.cmssystem.useradmin.controller;


//import com.cmssystem.useradmin.dto.UserAdminAddResponseDto;
//import com.cmssystem.useradmin.dto.UserAdminDetailsDto;
//import com.cmssystem.useradmin.dto.UserAdminResponseDto;
//import com.cmssystem.useradmin.dto.UserDeleteResponseDto;
import com.cmssystem.useradmin.dto.*;
import com.cmssystem.useradmin.entity.UserAdmin;
//import com.cmssystem.useradmin.service.UserAdminService;
import com.cmssystem.useradmin.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


        if (userAdminDetailsDto.getName() == null || userAdminDetailsDto.getPassword() == null ||
                userAdminDetailsDto.getEmail() == null)
        {
            userAdminAddResponseDto.setAdded(false);
            userAdminAddResponseDto.setMessage("User Not Created");
        }

        else {
            userAdminAddResponseDto = userAdminService.addUser(userAdminDetailsDto);
            userAdminAddResponseDto.setAdded(true);
            userAdminAddResponseDto.setMessage("User Created");
        }

        return new ResponseEntity<>(userAdminAddResponseDto, HttpStatus.OK);
    }


    @GetMapping(value = "/searchUser")
    public ResponseEntity<?> searchUser(@RequestParam(value = "name") String name){
        UserAdminResponseDto userAdminResponseDto = userAdminService.searchUser(name);
        return new ResponseEntity<>(userAdminResponseDto,HttpStatus.OK);
    }



    @GetMapping(value = "/getAllUsers")
    public ResponseEntity<List<UserAdmin>> getAllUsers(@RequestParam(value = "pageNumber") int pageNumber,@RequestParam(value = "pageSize") int pageSize){
        List<UserAdmin> userAdmins = userAdminService.getAllUsers(pageNumber,pageSize);
        return new ResponseEntity<>(userAdmins,HttpStatus.OK);
    }


    @DeleteMapping(value = "/userDeleteByDetails")
    public ResponseEntity<?> deleteUser(@RequestParam(value = "id") String id){
        UserDeleteResponseDto userDeleteResponseDto = userAdminService.deleteUser(id);
        return new ResponseEntity<>(userDeleteResponseDto, HttpStatus.OK);
    }


    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> authenticateLoginUser(@RequestBody UserLoginDto userLoginDto){

        boolean serviceResponse = true;
        UserLoginResponseDto userLoginResponseDto =new UserLoginResponseDto();

        if(userLoginDto.getEmail()==null|| userLoginDto.getPassword()==null || userLoginDto.getEmail().equals("") || userLoginDto.getPassword().equals("")) {
            userLoginResponseDto.setLogin(false);
            System.out.println("Invalid Login");
            return new ResponseEntity<>(userLoginResponseDto, HttpStatus.BAD_REQUEST);
        }

        userLoginResponseDto = userAdminService.authenticateLoginUser(userLoginDto.getEmail(),userLoginDto.getPassword());

        System.out.println("Login Done");
        return new ResponseEntity<>(userLoginResponseDto, HttpStatus.OK);
    }

}
