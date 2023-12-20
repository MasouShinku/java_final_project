package com.project.java.java_project.repository;

import com.project.java.java_project.model.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository <MediaEntity,Integer>{



}
