package com.project.java.java_project.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequest {
    private String name;
    private String password;
}