package com.mohaeyo.mohae.MoHaeServer.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "qa")
public class QA {
    int postId;

    String title;

    String username;

    String address;

    String summary;

    String imageUri;

    String description;

    List<Answer> answerList;

    public QA(int postId, String title, String username, String address, String summary, String imageUri, String description, List<Answer> answerList) {
        this.postId = postId;
        this.title = title;
        this.username = username;
        this.address = address;
        this.summary = summary;
        this.imageUri = imageUri;
        this.description = description;
        this.answerList = answerList;
    }

    public QA() {
        this.postId = 0;
        this.title = "";
        this.username = "";
        this.address = "";
        this.summary = "";
        this.imageUri = "";
        this.description = "";
        this.answerList = Collections.emptyList();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
