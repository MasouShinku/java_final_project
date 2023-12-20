package com.project.java.java_project.controller;

import com.project.java.java_project.dto.CommentDTO.GetCommentListResponse;
import com.project.java.java_project.dto.CommentDTO.PublishCommentRequest;
import com.project.java.java_project.dto.CommentDTO.PublishCommentResponse;
import com.project.java.java_project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * get方法，获取评论列表
     * @param id-题目id
     * @return GetCommentListResponse
     */
    @GetMapping("/getComment")
    @ResponseBody
    public ResponseEntity<?> getComment(@RequestParam int id){
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
