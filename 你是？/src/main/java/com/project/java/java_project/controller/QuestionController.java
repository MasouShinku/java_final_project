package com.project.java.java_project.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.java.java_project.dto.QuestionDetailResponse;
import com.project.java.java_project.dto.SearchQuestionRequest;
import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.model.QuestionsEntity;
import com.project.java.java_project.repository.QuestionRepository;
import com.project.java.java_project.service.QuestionService;
import jakarta.annotation.Nonnull;
import org.antlr.v4.runtime.misc.NotNull;
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

//    按条件筛选题目列表
//    POST请求

    /**
     * @param searchQuestionRequest
     * @return searchQuestionResponse
     */
    @PostMapping("/search")
    public ResponseEntity<?> searchQuestion(@RequestBody SearchQuestionRequest searchQuestionRequest){
        List<SearchQuestionResponse> searchQuestionResponse=questionService.searchQuestion(searchQuestionRequest.getLevel(), searchQuestionRequest.getDifficulty(), searchQuestionRequest.getSearchKey());
        return ResponseEntity.ok().body(searchQuestionResponse);
    }


    /**查看题目详情
     * @Param id 题目id
     * @return questionDetailResponse
     */
    @GetMapping("/detail")
    public ResponseEntity<?> getQuestionDetail(@RequestParam int id){
        QuestionDetailResponse questionDetailResponse=questionService.questionDetail(id);
        return ResponseEntity.ok().body(questionDetailResponse);
    }





}
