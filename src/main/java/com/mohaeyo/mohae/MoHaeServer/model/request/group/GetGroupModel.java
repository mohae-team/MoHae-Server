package com.mohaeyo.mohae.MoHaeServer.model.request.group;

public class GetGroupModel {
    String token;
    int postId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
