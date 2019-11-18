package com.mohaeyo.mohae.MoHaeServer.model.response;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;

public class ResponseGroupModel {
    int id;

    String title;

    String location;

    String address;

    String term;

    String summary;

    String imageUri;

    String description;

    int maxCount;

    int count;

    boolean isJoin;

    public ResponseGroupModel(int id, String title, String location, String address, String term,
                              String summary, String imageUri, String description, int maxCount, int count, boolean isJoin) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.address = address;
        this.term = term;
        this.summary = summary;
        this.imageUri = imageUri;
        this.description = description;
        this.maxCount = maxCount;
        this.count = count;
        this.isJoin = isJoin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
