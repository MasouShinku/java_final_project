package com.project.java.java_project.service;

import cn.dev33.satoken.stp.StpUtil;
import com.project.java.java_project.dto.CommentDTO.GetCommentListResponse;
import com.project.java.java_project.dto.CommentDTO.PublishCommentResponse;
import com.project.java.java_project.model.CommentEntity;
import com.project.java.java_project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public GetCommentListResponse getComment(Integer id){
        GetCommentListResponse getCommentListResponse=new GetCommentListResponse();
        getCommentListResponse.setGetCommentResponseList(commentRepository.findUserAndCommentTextByQuestionId(id));
        return getCommentListResponse;
    }

    public PublishCommentResponse publishComment(int questionId,String text){
        int userId= StpUtil.getLoginIdAsInt();

        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setText(text);
        commentEntity.setQuestionId(questionId);
        commentEntity.setUserId(userId);
        commentRepository.save(commentEntity);

        PublishCommentResponse publishCommentResponse=new PublishCommentResponse();
        publishCommentResponse.setMessage("success");
        return publishCommentResponse;
    }
}
