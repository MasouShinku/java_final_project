package com.project.java.java_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCommentResponse {
    private String username;
    private String text;

    public GetCommentResponse(String username,String text){
        this.username=username;
        this.text=text;
    }
}
