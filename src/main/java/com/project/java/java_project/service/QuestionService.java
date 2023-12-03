package com.project.java.java_project.service;


import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<SearchQuestionResponse> searchQuestion(int level, int difficulty, String key){
        System.out.println("level is : "+level);
        System.out.println("difficulty is : "+difficulty);
        System.out.println("key is : "+key);

        List<SearchQuestionResponse> searchQuestionReponse=questionRepository.searchQuestions(level,difficulty,key);
        System.out.println("查找题目成功！数量为："+searchQuestionReponse.size());

        return searchQuestionReponse;

    }

}
