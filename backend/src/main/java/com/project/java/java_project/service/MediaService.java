package com.project.java.java_project.service;

import com.project.java.java_project.dto.UploadMediaRequest;
import com.project.java.java_project.model.MediaEntity;
import com.project.java.java_project.repository.MediaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

}
