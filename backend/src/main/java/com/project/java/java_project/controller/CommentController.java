package com.project.java.java_project.controller;

import com.project.java.java_project.dto.*;
import com.project.java.java_project.service.CommentService;
import com.project.java.java_project.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getComment")
    @ResponseBody
    public ResponseEntity<?> getQuestionDetail(@RequestParam int id){
        GetCommentListResponse response=commentService.getComment(id);
        return ResponseEntity.ok().body(response);
    }


    /**发布评论
     * 需要角色为user才可以使用
     * @param publishCommentRequest
     * @return
     */
    @PostMapping("/publishComment")
    public ResponseEntity<?> publishComment(@RequestBody PublishCommentRequest publishCommentRequest){
        PublishCommentResponse publishCommentResponse=commentService.publishComment(publishCommentRequest.getQuestionId(), publishCommentRequest.getText());
        return ResponseEntity.ok().body(publishCommentResponse);
    }
}
