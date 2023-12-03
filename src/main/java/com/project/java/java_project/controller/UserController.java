package com.project.java.java_project.controller;

import com.project.java.java_project.dto.UserLoginRequest;
import com.project.java.java_project.dto.UserLoginResponse;
import com.project.java.java_project.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest userLoginRequest){
        System.out.println("controller:");
        System.out.println("user name is : "+userLoginRequest.getName());
        System.out.println("user password is : "+userLoginRequest.getPassword());
        UserLoginResponse userLoginResponse=userService.getUserToken(userLoginRequest.getName(),userLoginRequest.getPassword());
        return ResponseEntity.ok().body(userLoginResponse);
    }

}
