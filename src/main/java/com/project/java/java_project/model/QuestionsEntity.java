package com.project.java.java_project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Questions", schema = "java", catalog = "")
public class QuestionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "topic")
    private String topic;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "difficulty")
    private int difficulty;
    @Basic
    @Column(name = "user_id")
    private int userId;
    @Basic
    @Column(name = "level")
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionsEntity that = (QuestionsEntity) o;
        return id == that.id && difficulty == that.difficulty && userId == that.userId && level == that.level && Objects.equals(topic, that.topic) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, description, difficulty, userId, level);
    }

    @OneToMany(mappedBy = "questionsEntity")
//    @JsonManagedReference
    private List<MediaEntity> mediaEntityList;


    public List<MediaEntity> getMediaEntityList() {
        return this.mediaEntityList;
    }

}
