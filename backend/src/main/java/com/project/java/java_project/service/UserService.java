package com.project.java.java_project.service;

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

//    用户存在则返回token
//    public String getUserToken(String name,String password){
//        System.out.println("service:");
//        System.out.println("user name is : "+name);
//        System.out.println("user password is : "+password);
//
//        Optional<UsersEntity> user=userRepository.findUsersEntitiesByNameAndPassword(name,password);
//        if(user.isPresent()){
//            return "token example";
//        }
//        return null;
//
//    }




    public UserLoginResponse getUserToken(String name, String password){
        System.out.println("service:");
        System.out.println("user name is : "+name);
        System.out.println("user password is : "+password);

        Optional<UsersEntity> user=userRepository.findUsersEntitiesByNameAndPassword(name,password);
        System.out.println("查找用户成功！isPresent= "+user.isPresent());
        UserLoginResponse userLoginResponse=new UserLoginResponse();
        if(user.isPresent()){
            userLoginResponse.setStatus(true);
            userLoginResponse.setToken("this is a token");
            return userLoginResponse;
        }
        return userLoginResponse;

    }


}
