package com.mohaeyo.mohae.MoHaeServer.model.request.mypage;

import java.util.List;

public class EditMyPageModel {
    String token;

    List<Byte> imageByteList;

    String username;

    String address;

    String description;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Byte> getImageByteList() {
        return imageByteList;
    }

    public void setImageByteList(List<Byte> imageByteList) {
        this.imageByteList = imageByteList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}