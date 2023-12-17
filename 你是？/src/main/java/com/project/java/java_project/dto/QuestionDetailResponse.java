package com.project.java.java_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDetailResponse {

    private String title;
    private String description;
    private String level;
    private String difficulty;
    private String photoUrl;
    private String videoUrl;
    private String audioUrl;

}
