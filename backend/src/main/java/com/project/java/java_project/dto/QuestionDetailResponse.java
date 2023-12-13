package com.project.java.java_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDetailResponse {

    private String title;
    private String description;
    private String level;
    private String difficulty;
    private List<String> photoUrl;
    private List<String> videoUrl;
    private List<String> audioUrl;

}
