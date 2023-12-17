package com.project.java.java_project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SearchQuestionResponse {
    @JsonIgnore
    private final String[] levelArr= new String[]{"学前","小学","中学","大学","其他"};
    @JsonIgnore
    private final String[] difficultyArr= new String[]{"简单","普通","困难"};


    private int id;
    private String title;
    private String description;
//    private int originalLevel;
    private String level;
//    private int originalDifficulty;
    private String difficulty;

    public SearchQuestionResponse(int id,String title,String description,int originalLevel,int originalDifficulty){
        this.id=id;
        this.title=title;
        this.description=description;

        this.level=levelArr[originalLevel-1];
        this.difficulty=difficultyArr[originalDifficulty-1];
    }


}
