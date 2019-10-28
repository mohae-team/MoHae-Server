package com.mohaeyo.mohae.MoHaeServer.model.entity;

public class Answer {
    String username;

    String answer;

    public Answer(String username, String answer) {
        this.username = username;
        this.answer = answer;
    }

    public Answer() {
        this.username = "";
        this.answer = "";
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
