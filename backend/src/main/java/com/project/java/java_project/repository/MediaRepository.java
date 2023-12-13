package com.project.java.java_project.repository;

import com.project.java.java_project.dto.GetCommentResponse;
import com.project.java.java_project.model.CommentEntity;
import com.project.java.java_project.model.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository <MediaEntity,Integer>{



}
