package com.mohaeyo.mohae.MoHaeServer.model.request.qa;

public class CreateAnswerModel {
    int postId;

    String answer;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
