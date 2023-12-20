package com.project.java.java_project.dto.CommentDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublishCommentRequest {
    private int questionId;
    private String text;
}
