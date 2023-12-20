package com.project.java.java_project.repository;



import com.project.java.java_project.dto.QuestionDTO.SearchQuestionResponse;
import com.project.java.java_project.model.QuestionsEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionsEntity,Integer> {


    /**根据筛选条件返回题目列表
     *
     * @param level         级别，-1为不限
     * @param difficulty    难度，-1为不限
     * @param keyword       关键字，可为空串
     * @return List<SearchQuestionResponse>
     */
    @Query("SELECT new com.project.java.java_project.dto.QuestionDTO.SearchQuestionResponse(q.id, q.topic, q.description, q.level, q.difficulty) " +
    "FROM QuestionsEntity q WHERE " +
    "(q.level = :level OR :level = -1) " +
    "AND (q.difficulty = :difficulty OR :difficulty = -1) " +
    "AND ((q.description LIKE concat('%',:keyword,'%') )or(q.topic LIKE concat('%',:keyword,'%') ) )")
    List<SearchQuestionResponse> searchQuestions(@Param("level") Integer level,
                                                 @Param("difficulty") Integer difficulty,
                                                 @Param("keyword") String keyword);


    /** 获取题目的媒体列表
     *
     * @param id 题目id
     * @return QuestionsEntity
     */
    @EntityGraph(attributePaths = {"mediaEntityList"})
    Optional<QuestionsEntity> findById(@Param("id") int id);








}
