package com.project.java.java_project.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.project.java.java_project.dto.UserDTO.UserLoginRequest;
import com.project.java.java_project.dto.UserDTO.UserLoginResponse;
import com.project.java.java_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;


    /** 登录方法
     *用户不存在时创建新用户
     * @param userLoginRequest
     * @return userLoginResponse
     */
    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest userLoginRequest){
        UserLoginResponse userLoginResponse=userService.getUserToken(userLoginRequest.getName(),userLoginRequest.getPassword());
        return ResponseEntity.ok().body(userLoginResponse);
    }


}
