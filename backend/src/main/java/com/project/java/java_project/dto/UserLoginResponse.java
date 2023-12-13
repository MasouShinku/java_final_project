package com.project.java.java_project.dto;


import cn.dev33.satoken.stp.SaTokenInfo;
import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginResponse {
    boolean status;
    String token;
}
