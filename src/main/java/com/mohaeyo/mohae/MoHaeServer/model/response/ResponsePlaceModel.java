package com.mohaeyo.mohae.MoHaeServer.model.response;

public class ResponsePlaceModel {
    String placeName;

    String location;

    String description;

    boolean isLike;

    public ResponsePlaceModel(String placeName, String location, String description, boolean isLike) {
        this.placeName = placeName;
        this.location = location;
        this.description = description;
        this.isLike = isLike;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
