package com.project.java.java_project.dto.UserDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String name;
    private String password;
}