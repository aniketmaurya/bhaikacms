package com.cmssystem.useradmin.controller;


import com.cmssystem.useradmin.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/useradmin")
public class UserAdminController {


    @Autowired
    private UserAdminService userAdminService;

    @PostMapping(value = "/addUserAdmin")
    public ResponseEntity<?> addUser(@RequestBody UserAdminDetailsDto userDetailsDto) {
        String response = "";
        UserSignUpDto userSignUpDto = new UserSignUpDto();

        boolean serviceResponse = true;

        if (userDetailsDto.getFirstName() == null || userDetailsDto.getPassword() == null ||
                userDetailsDto.getEmail() == null || userDetailsDto.getLastName() == null || userDetailsDto.getAddress() == null ||
                userDetailsDto.getPhoneNumber() == null)
            serviceResponse = false;
        else {
            serviceResponse = userService.addUser(userDetailsDto);
            userSignUpDto.setEmail(userDetailsDto.getEmail());
        }

        if (serviceResponse) {
            userSignUpDto.setMessage("User is Created.");
            return new ResponseEntity<>(userSignUpDto, HttpStatus.OK);
        } else {
            userSignUpDto.setMessage("User is not Created.");
            return new ResponseEntity<>(userSignUpDto, HttpStatus.OK);
        }
    }


}
