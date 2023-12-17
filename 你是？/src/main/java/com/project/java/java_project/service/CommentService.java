package com.project.java.java_project.service;

import com.project.java.java_project.dto.GetCommentResponse;
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
}
