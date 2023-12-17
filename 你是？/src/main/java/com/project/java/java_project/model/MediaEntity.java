package com.project.java.java_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Media", schema = "java", catalog = "")
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
//    @Basic
//    @Column(name = "QuestionID")
//    private Integer questionId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaEntity that = (MediaEntity) o;
        return mediaId == that.mediaId && type == that.type && Objects.equals(url, that.url) && Objects.equals(questionsEntity, that.questionsEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaId, type, url, questionsEntity);
    }

    @ManyToOne
    @JoinColumn(name = "QuestionID", referencedColumnName = "id")
    @JsonIgnore
    private QuestionsEntity questionsEntity;

    public QuestionsEntity getQuestionsEntity() {
        return questionsEntity;
    }

    public void setQuestionsEntity(QuestionsEntity questionsEntity) {
        this.questionsEntity = questionsEntity;
    }


}
