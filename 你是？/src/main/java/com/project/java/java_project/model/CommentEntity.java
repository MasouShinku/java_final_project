package com.project.java.java_project.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Comment", schema = "java", catalog = "")
public class CommentEntity {
    @Basic
    @Column(name = "user_id")
    private int userId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "question_id")
    private int questionId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return userId == that.userId && id == that.id && questionId == that.questionId && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, id, text, questionId);
    }
}
