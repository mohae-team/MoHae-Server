package com.mohaeyo.mohae.MoHaeServer.model.request.qa;

public class CreateAnswerModel {
    int questionId;

    String answer;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
