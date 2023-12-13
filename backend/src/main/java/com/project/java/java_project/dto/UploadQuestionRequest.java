package com.project.java.java_project.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.MutableCallSite;
import java.util.List;

@Getter
@Setter
public class UploadQuestionRequest {
    private int userId;
    private String level;
    private String difficulty;
    private String title;
    private String description;
    private List<MultipartFile> fileList;
}
