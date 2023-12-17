package com.project.java.java_project.repository;

import com.project.java.java_project.dto.GetCommentResponse;
import com.project.java.java_project.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository <CommentEntity,Integer>{

    @Query("SELECT new com.project.java.java_project.dto.GetCommentResponse(u.name,c.text) FROM UsersEntity u JOIN CommentEntity c ON u.id = c.userId WHERE c.questionId = :id")
    List<GetCommentResponse> findUserAndCommentTextByQuestionId(@Param("id") Integer id);
}
