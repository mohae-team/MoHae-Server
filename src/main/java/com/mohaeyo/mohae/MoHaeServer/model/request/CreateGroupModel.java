package com.mohaeyo.mohae.MoHaeServer.model.request;

import java.util.List;

public class CreateGroupModel {
    String token;
    String title;
    String location;
    String address;
    String term;
    String summary;
    int maxCount;
    List<Byte> imageByteList;
    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Byte> getImageByteList() {
        return imageByteList;
    }

    public void setImageByteList(List<Byte> imageByteList) {
        this.imageByteList = imageByteList;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
