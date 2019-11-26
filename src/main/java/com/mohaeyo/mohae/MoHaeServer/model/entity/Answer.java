package com.mohaeyo.mohae.MoHaeServer.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answer")
public class Answer {

    int id;

    int questionId;

    String username;

    String answer;

    public Answer(int id, int questionId, String username, String answer) {
        this.id = id;
        this.questionId = questionId;
        this.username = username;
        this.answer = answer;
    }

    public Answer() {
        this.username = "";
        this.answer = "";
        this.id = 0;
        this.questionId = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
