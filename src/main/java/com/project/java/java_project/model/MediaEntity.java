package com.project.java.java_project.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Media", schema = "java")
public class MediaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "MediaID")
    private int mediaId;
    @Basic
    @Column(name = "Type")
    private int type;
    @Basic
    @Column(name = "url")
    private String url;
    @Basic
    @Column(name = "QuestionID")
    private Integer questionId;

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaEntity that = (MediaEntity) o;
        return mediaId == that.mediaId && type == that.type && Objects.equals(url, that.url) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaId, type, url, questionId);
    }
}
