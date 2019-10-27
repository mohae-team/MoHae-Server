package com.mohaeyo.mohae.MoHaeServer.model.request.feedback;

import java.util.List;

public class CreateFeedbackModel {

    String location;

    String address;

    String summary;

    List<Byte> imageByteList;

    String description;

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

    public List<Byte> getImageByteList() {
        return imageByteList;
    }

    public void setImageByteList(List<Byte> imageByteList) {
        this.imageByteList = imageByteList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
