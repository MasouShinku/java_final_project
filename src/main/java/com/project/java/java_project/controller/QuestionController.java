package com.project.java.java_project.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.java.java_project.dto.QuestionDetailResponse;
import com.project.java.java_project.dto.SearchQuestionRequest;
import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.model.QuestionsEntity;
import com.project.java.java_project.repository.QuestionRepository;
import com.project.java.java_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

//    按条件筛选题目列表
//    POST请求
    @PostMapping("/search")
    public ResponseEntity<?> searchQuestion(@RequestBody SearchQuestionRequest searchQuestionRequest){
        List<SearchQuestionResponse> searchQuestionReponse=questionService.searchQuestion(searchQuestionRequest.getLevel(), searchQuestionRequest.getDifficulty(), searchQuestionRequest.getSearchKey());
        return ResponseEntity.ok().body(searchQuestionReponse);
    }

//    按题目id查看题目详情
//    GET请求，形如?id={id}
    @GetMapping("/detail")
    public ResponseEntity<?> getQuestionDetail(@RequestParam int id){
//        JSONObject jsonObject = new JSONObject();
        QuestionDetailResponse questionDetailResponse=questionService.questionDetail(id);
//        System.out.println(questionsEntity.get().getMediaEntityList());


        return ResponseEntity.ok().body(questionDetailResponse);
    }





}
