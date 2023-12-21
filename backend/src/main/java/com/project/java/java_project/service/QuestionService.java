package com.project.java.java_project.service;


import cn.dev33.satoken.stp.StpUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.java.java_project.dto.QuestionDTO.ImportQuestionResponse;
import com.project.java.java_project.dto.QuestionDTO.QuestionDetailResponse;
import com.project.java.java_project.dto.QuestionDTO.SearchQuestionResponse;
import com.project.java.java_project.dto.QuestionDTO.UploadQuestionResponse;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            if (eachMedia.getType() == 2)
                videoList.add(eachMedia.getUrl());
            if (eachMedia.getType() == 3)
                audioList.add(eachMedia.getUrl());
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

                mediaEntity.setUrl(fileInfo.getUrl());
                if(fileInfo.getExt().equals("png")||fileInfo.getExt().equals("jpg"))
                    mediaEntity.setType(1);
                else if(fileInfo.getExt().equals("mp4"))
                    mediaEntity.setType(2);
                else mediaEntity.setType(3);
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




    public ImportQuestionResponse importQuestion(MultipartFile file) {
        //        首先获取一下userId
        int userId= StpUtil.getLoginIdAsInt();
        System.out.println("user id is "+userId);
        List<QuestionsEntity> questions=new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            String difficulty;
            String level;
            StringBuilder descriptionBuilder = new StringBuilder();
            QuestionsEntity currentQuestion=new QuestionsEntity();



            while ((line = reader.readLine()) != null) {
                if (line.startsWith("标题")) {
                    if (currentQuestion.getTopic() != null) {
                        currentQuestion.setDescription(descriptionBuilder.toString().trim()) ;
                        System.out.println("描述为："+currentQuestion.getDescription());

//                        System.out.println("===============================================");
//                        System.out.println("标题为："+currentQuestion.getTopic());

                        currentQuestion.setUserId(userId);
                        questions.add(currentQuestion);
                    }
                    currentQuestion = new QuestionsEntity();
                    descriptionBuilder = new StringBuilder();
                    currentQuestion.setTopic(line.substring(2).trim()) ;
                    System.out.println("===============================================");
                    System.out.println("标题为："+currentQuestion.getTopic());
                } else if (line.startsWith("难度")) {
                    difficulty=line.substring(2).trim();
                    System.out.println("难度为："+difficulty);

                    if(difficulty.equals("简单"))
                        currentQuestion.setDifficulty(1);
                    else if(difficulty.equals("普通"))
                        currentQuestion.setDifficulty(2);
                    else if (difficulty.equals("困难"))
                        currentQuestion.setDifficulty(3);
                    else {
                        ImportQuestionResponse importQuestionResponse=new ImportQuestionResponse();
                        importQuestionResponse.setMessage("难度出错，请检查");
                    }
                } else if (line.startsWith("等级")) {
                    level=line.substring(2).trim();
                    System.out.println("等级为："+level);
                    if(level.equals("学前"))
                        currentQuestion.setLevel(1);
                    else if(level.equals("小学"))
                        currentQuestion.setLevel(2);
                    else if (level.equals("中学"))
                        currentQuestion.setLevel(3);
                    else if (level.equals("大学"))
                        currentQuestion.setLevel(4);
                    else if (level.equals("其他"))
                        currentQuestion.setLevel(5);
                    else {
                        ImportQuestionResponse importQuestionResponse=new ImportQuestionResponse();
                        importQuestionResponse.setMessage("等级出错，请检查");
                    }
                } else if(line.startsWith("题目描述")){
                    descriptionBuilder.append(line.substring(4).trim()).append("\n");
                }
                else {
                    // 累加描述的内容
                    descriptionBuilder.append(line).append("\n");
                }
            }
            // 添加最后一个问题
            currentQuestion.setDescription( descriptionBuilder.toString().trim());
            System.out.println("描述为："+currentQuestion.getDescription());

            currentQuestion.setUserId(userId);
            questions.add(currentQuestion);


            questionRepository.saveAll(questions);
        } catch (IOException e) {
            e.printStackTrace();
            ImportQuestionResponse importQuestionResponse=new ImportQuestionResponse();
            importQuestionResponse.setMessage("failed");
            return importQuestionResponse;
        }


        ImportQuestionResponse importQuestionResponse=new ImportQuestionResponse();
        importQuestionResponse.setMessage("success");


        return importQuestionResponse;
    }


}
