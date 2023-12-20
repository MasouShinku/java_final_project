package com.project.java.java_project.service;

import com.project.java.java_project.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

}
