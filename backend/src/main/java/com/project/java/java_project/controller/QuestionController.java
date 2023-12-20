package com.project.java.java_project.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.project.java.java_project.dto.QuestionDTO.*;
import com.project.java.java_project.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;


@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;



    /**按条件查询题目，返回题目列表
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



    /**上传题目，请求为form-data形式
     * 需要角色为user才可以使用
     * @param request 表单请求对象
     * @return uploadQuestionResponse
     */
    @SaCheckRole("user")
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


    /**导入题目，请求为form-data形式
     * 需要角色为user才可以使用
     */
    @SaCheckRole("user")
    @PostMapping("/import")
    public ResponseEntity<?> importQuestion( HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        MultipartFile file=((MultipartHttpServletRequest) request).getFile("file");
        ImportQuestionResponse importQuestionResponse= questionService.importQuestion(file);
        return ResponseEntity.ok().body(importQuestionResponse);
    }






}
