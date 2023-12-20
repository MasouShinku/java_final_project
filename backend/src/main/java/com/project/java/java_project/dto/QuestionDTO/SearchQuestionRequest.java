package com.project.java.java_project.dto.QuestionDTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchQuestionRequest {
    int level;
    int difficulty;
    String searchKey;

}
