package com.project.java.java_project.dto;


import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse {
    boolean status;
    String token;
}
