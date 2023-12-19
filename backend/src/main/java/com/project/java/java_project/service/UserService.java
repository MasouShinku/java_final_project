package com.project.java.java_project.service;

import cn.dev33.satoken.stp.StpUtil;
import com.project.java.java_project.dto.UserLoginResponse;
import com.project.java.java_project.model.UsersEntity;
import com.project.java.java_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public UserLoginResponse getUserToken(String name, String password){
        System.out.println("service:");
        System.out.println("user name is : "+name);
        System.out.println("user password is : "+password);

        Optional<UsersEntity> user=userRepository.findUsersEntitiesByNameAndPassword(name,password);
        System.out.println("查找用户成功！isPresent= "+user.isPresent());
        UserLoginResponse userLoginResponse=new UserLoginResponse();
        if(user.isPresent()){
//            sa-token设置登录
            StpUtil.login(user.get().getId());
            userLoginResponse.setStatus(true);
            userLoginResponse.setToken(StpUtil.getTokenInfo().tokenValue);
            return userLoginResponse;
        }
        // 否则直接创建用户
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setName(name);
        usersEntity.setPassword(password);
        UsersEntity savedUser=userRepository.save(usersEntity);
        StpUtil.login(savedUser.getId());
        userLoginResponse.setStatus(true);
        userLoginResponse.setToken(StpUtil.getTokenInfo().tokenValue);
        return userLoginResponse;

    }


}
