package com.project.java.java_project.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.java.java_project.dto.*;
import com.project.java.java_project.model.QuestionsEntity;
import com.project.java.java_project.repository.QuestionRepository;
import com.project.java.java_project.service.QuestionService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Enumeration;
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

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadQuestion(@RequestBody UploadQuestionRequest uploadQuestionRequest){
//        UploadQuestionResponse uploadQuestionResponse= questionService.uploadQuestion(uploadQuestionRequest.getUserId(),uploadQuestionRequest.getTitle(),uploadQuestionRequest.getDescription(),uploadQuestionRequest.getLevel(),uploadQuestionRequest.getDifficulty());
//        return ResponseEntity.ok().body(uploadQuestionResponse);
//    }


//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadQuestion(@RequestPart UploadQuestionRequest uploadQuestionRequest){
//        UploadQuestionResponse uploadQuestionResponse= questionService.uploadQuestion(uploadQuestionRequest.getUserId(),uploadQuestionRequest.getTitle(),uploadQuestionRequest.getDescription(),uploadQuestionRequest.getLevel(),uploadQuestionRequest.getDifficulty(),uploadQuestionRequest.getFileList());
//        return ResponseEntity.ok().body(uploadQuestionResponse);
//    }

//    @PostMapping("/upload")
//    public ResponseEntity<?> uploadQuestion(
//            @RequestPart("userId") int userId,
//            @RequestPart("level") String level,
//            @RequestPart("difficulty") String difficulty,
//            @RequestPart("title") String title,
//            @RequestPart("description") String description,
//            @RequestPart("fileList") List<MultipartFile> fileList
//    ){
//        UploadQuestionResponse uploadQuestionResponse= questionService.uploadQuestion(userId,title,description,level,difficulty,fileList);
//        return ResponseEntity.ok().body(uploadQuestionResponse);
//    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadQuestion( HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);

        List<MultipartFile> fileList=((MultipartHttpServletRequest) request).getFiles("fileList");
        System.out.println(fileList.size());
        for(MultipartFile file : fileList)
            System.out.println(file.getOriginalFilename());

        UploadQuestionResponse uploadQuestionResponse= questionService.uploadQuestion(params.getParameter("title"),params.getParameter("description"),params.getParameter("level"),params.getParameter("difficulty"),fileList);
        return ResponseEntity.ok().body(uploadQuestionResponse);
    }






}
