package com.project.java.java_project.controller;

import com.project.java.java_project.dto.GetCommentResponse;
import com.project.java.java_project.dto.QuestionDetailResponse;
import com.project.java.java_project.service.CommentService;
import com.project.java.java_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getComment")
    public ResponseEntity<?> getQuestionDetail(@RequestParam int id){
        List<GetCommentResponse> response=commentService.getComment(id);
        return ResponseEntity.ok().body(response);
    }
}
