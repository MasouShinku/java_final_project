package com.project.java.java_project.service;

import com.project.java.java_project.dto.GetCommentResponse;
import com.project.java.java_project.dto.PublishCommentResponse;
import com.project.java.java_project.model.CommentEntity;
import com.project.java.java_project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<GetCommentResponse> getComment(Integer id){
        return commentRepository.findUserAndCommentTextByQuestionId(id);
    }

    public PublishCommentResponse publishComment(int userId,int questionId,String text){
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
