package com.mohaeyo.mohae.MoHaeServer.model.response;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Feedback;

public class ResponseFeedbackModel {
    Feedback feedback;
    boolean isLike;
    boolean isHate;

    public ResponseFeedbackModel(Feedback feedback, boolean isLike, boolean isHate) {
        this.feedback = feedback;
        this.isLike = isLike;
        this.isHate = isHate;
    }

    public boolean isHate() {
        return isHate;
    }

    public void setHate(boolean hate) {
        isHate = hate;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
