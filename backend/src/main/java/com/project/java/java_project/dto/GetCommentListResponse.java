package com.project.java.java_project.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCommentListResponse {
    private List<GetCommentResponse> getCommentResponseList;
}
