package com.project.java.java_project.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.java_project.dto.QuestionDetailResponse;
import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.model.MediaEntity;
import com.project.java.java_project.model.QuestionsEntity;
import com.project.java.java_project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @JsonIgnore
    private final String[] levelArr= new String[]{"\uD83E\uDD70学前\uD83D\uDE3B","\uD83D\uDE01小学\uD83E\uDD2A","\uD83E\uDD14中学\uD83D\uDE3C","\uD83E\uDD74大学\uD83D\uDE35","\uD83D\uDE28其他\uD83D\uDE33"};
    @JsonIgnore
    private final String[] difficultyArr= new String[]{"\uD83E\uDD24简单\uD83D\uDE0E","\uD83D\uDE2F普通\uD83E\uDD2F","\uD83D\uDE08困难\uD83D\uDE40"};

    @Autowired
    private QuestionRepository questionRepository;

    public List<SearchQuestionResponse> searchQuestion(int level, int difficulty, String key){
        System.out.println("QuestionService.searchQuestion: ");
        System.out.println("level is : "+level);
        System.out.println("difficulty is : "+difficulty);
        System.out.println("key is : "+key);

        List<SearchQuestionResponse> searchQuestionResponse=questionRepository.searchQuestions(level,difficulty,key);
        System.out.println("查找题目成功！数量为："+searchQuestionResponse.size());

        return searchQuestionResponse;
    }

    public QuestionDetailResponse questionDetail(int id){
        System.out.println("QuestionService.questionDetail: ");
        System.out.println("id is : "+id);
        Optional<QuestionsEntity> questionsEntity=questionRepository.findById(id);
        if (questionsEntity.isEmpty())
            return null;
        QuestionDetailResponse questionDetailResponse= new QuestionDetailResponse();
        questionDetailResponse.setTitle(questionsEntity.get().getTopic());
        questionDetailResponse.setDescription(questionsEntity.get().getDescription());
        questionDetailResponse.setDifficulty(difficultyArr[questionsEntity.get().getDifficulty()-1]);
        questionDetailResponse.setLevel(levelArr[questionsEntity.get().getLevel()-1]);
        for(MediaEntity eachMedia : questionsEntity.get().getMediaEntityList()) {
            if (eachMedia.getType() == 1)
                questionDetailResponse.setPhotoUrl(eachMedia.getUrl());
            if (eachMedia.getType() == 2)
                questionDetailResponse.setVideoUrl(eachMedia.getUrl());
            if (eachMedia.getType() == 3)
                questionDetailResponse.setAudioUrl(eachMedia.getUrl());
        }
        return questionDetailResponse;
//        questionDetailResponse.set

    }

}
