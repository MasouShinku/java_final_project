package com.project.java.java_project.repository;



import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.model.QuestionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionsEntity,Integer> {

//    根据用户传入参数返回题目
//    传入-1说明不限
//    keyword为""不需要专门处理
    @Query("SELECT new com.project.java.java_project.dto.SearchQuestionResponse(q.id, q.topic, q.description, q.level, q.difficulty) " +
    "FROM QuestionsEntity q WHERE " +
    "(q.level = :level OR :level = -1) " +
    "AND (q.difficulty = :difficulty OR :difficulty = -1) " +
    "AND (q.description LIKE concat('%',:keyword,'%') )")
    List<SearchQuestionResponse> searchQuestions(@Param("level") Integer level,
                                                 @Param("difficulty") Integer difficulty,
                                                 @Param("keyword") String keyword);


//    @Query("SELECT q FROM QuestionsEntity q LEFT JOIN FETCH q.mediaEntityList m WHERE q.id = :id")
//    Optional<QuestionsEntity> findByIdWithMedia(@Param("id") Long id);







}
