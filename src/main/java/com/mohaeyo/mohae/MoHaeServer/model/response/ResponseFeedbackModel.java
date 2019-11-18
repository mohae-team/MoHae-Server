package com.mohaeyo.mohae.MoHaeServer.model.response;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Feedback;

public class ResponseFeedbackModel {
    int id;

    String placeName;

    String location;

    String address;

    String summary;

    String imageUri;

    String description;

    int likeCount;

    int hateCount;

    boolean isLike;

    boolean isHate;

    public ResponseFeedbackModel(int id, String placeName, String location, String address, String summary,
                                 String imageUri, String description, int likeCount, int hateCount, boolean isLike, boolean isHate) {
        this.id = id;
        this.placeName = placeName;
        this.location = location;
        this.address = address;
        this.summary = summary;
        this.imageUri = imageUri;
        this.description = description;
        this.likeCount = likeCount;
        this.hateCount = hateCount;
        this.isLike = isLike;
        this.isHate = isHate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getHateCount() {
        return hateCount;
    }

    public void setHateCount(int hateCount) {
        this.hateCount = hateCount;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public boolean isHate() {
        return isHate;
    }

    public void setHate(boolean hate) {
        isHate = hate;
    }
}
