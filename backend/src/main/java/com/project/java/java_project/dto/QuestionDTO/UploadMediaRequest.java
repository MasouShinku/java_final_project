package com.project.java.java_project.dto.QuestionDTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadMediaRequest {
    private MultipartFile file;
    private int questionId;
}
