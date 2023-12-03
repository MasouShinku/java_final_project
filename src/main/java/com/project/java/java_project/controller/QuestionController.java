package com.project.java.java_project.controller;


import com.project.java.java_project.dto.SearchQuestionRequest;
import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

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
    public ResponseEntity<?> getQuestionDetail(@RequestParam String id){
        return ResponseEntity.ok().body(id);
    }





}
