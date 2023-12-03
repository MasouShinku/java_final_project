package com.project.java.java_project.controller;


//import com.project.java.java_project.model.UsersEntity;
import com.project.java.java_project.model.UsersEntity;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/get")
public class getExample {

//    标记是否是api
    @RequestMapping(value = "example",method = RequestMethod.GET)
    public String example(String item){
        return "this is an example for： "+item;
    }

    @RequestMapping(value = "user",method = RequestMethod.GET)
    public UsersEntity getUserInfo(){
        return new UsersEntity();
    }
}
