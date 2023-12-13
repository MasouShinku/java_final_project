package com.project.java.java_project.service;


import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.java_project.dto.QuestionDetailResponse;
import com.project.java.java_project.dto.SearchQuestionResponse;
import com.project.java.java_project.dto.UploadQuestionResponse;
import com.project.java.java_project.model.MediaEntity;
import com.project.java.java_project.model.QuestionsEntity;
import com.project.java.java_project.repository.MediaRepository;
import com.project.java.java_project.repository.QuestionRepository;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.util.ArrayList;
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

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private FileStorageService fileStorageService;

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

//       接下来获取媒体列表
        List<String> photoList=new ArrayList<>();
        List<String> videoList=new ArrayList<>();
        List<String> audioList=new ArrayList<>();
        for(MediaEntity eachMedia : questionsEntity.get().getMediaEntityList()) {
            if (eachMedia.getType() == 1)
                photoList.add(eachMedia.getUrl());
//                questionDetailResponse.setPhotoUrl(eachMedia.getUrl());
            if (eachMedia.getType() == 2)
                videoList.add(eachMedia.getUrl());
//                questionDetailResponse.setVideoUrl(eachMedia.getUrl());
            if (eachMedia.getType() == 3)
                audioList.add(eachMedia.getUrl());
//                questionDetailResponse.setAudioUrl(eachMedia.getUrl());
        }
        questionDetailResponse.setPhotoUrl(photoList);
        questionDetailResponse.setVideoUrl(videoList);
        questionDetailResponse.setAudioUrl(audioList);


        return questionDetailResponse;
//        questionDetailResponse.set
    }

    public UploadQuestionResponse uploadQuestion( String title, String description, String level, String difficulty, List<MultipartFile> fileList){
//        首先获取一下userId
        int userId= StpUtil.getLoginIdAsInt();
        System.out.println("user id is "+userId);




        for(MultipartFile file : fileList)
            System.out.println(file.getOriginalFilename());

        QuestionsEntity questionsEntity=new QuestionsEntity();
        UploadQuestionResponse uploadQuestionResponse=new UploadQuestionResponse();
        for(int i=0;i<levelArr.length;i++)
            if (levelArr[i].equals(level))
                questionsEntity.setLevel(i + 1);

        for(int i=0;i<difficultyArr.length;i++)
            if(difficultyArr[i].equals(difficulty))
                questionsEntity.setDifficulty(i+1);
        questionsEntity.setTopic(title);
        questionsEntity.setDescription(description);
        questionsEntity.setUserId(userId);


//        questionRepository.save(questionsEntity);
        try {
            QuestionsEntity savedQuestionEntity = questionRepository.save(questionsEntity);
            // 保存成功，可以继续处理
            System.out.println("Save successful. Entity ID: " + savedQuestionEntity.getId());


            // 接下来进行媒体的保存
            for (MultipartFile file : fileList) {
//                直接调用x-file方法
                FileInfo fileInfo = fileStorageService.of(file).setName(file.getOriginalFilename()).upload();
                if(fileInfo!=null)
                    System.out.println(String.format("文件%s储存成功",fileInfo.getUrl()));
                else return null;

//                数据库插入媒体实体
                MediaEntity mediaEntity = new MediaEntity();
                mediaEntity.setQuestionsEntity(savedQuestionEntity);
                mediaEntity.setType(1);
                mediaEntity.setUrl(fileInfo.getUrl());
                mediaRepository.save(mediaEntity);





                System.out.println(fileInfo.getUrl());

            }






            uploadQuestionResponse.setMessage("success");
        } catch (DataIntegrityViolationException e) {
            // 保存失败，处理相应的异常情况
            System.out.println("Save failed due to data integrity violation.");
            e.printStackTrace();
            uploadQuestionResponse.setMessage("fail");
        } catch (Exception e) {
            // 其他异常情况的处理
            System.out.println("An unexpected error occurred during save.");
            e.printStackTrace();
            uploadQuestionResponse.setMessage("fail");
        }

        return uploadQuestionResponse;


    }

}
