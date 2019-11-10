package com.mohaeyo.mohae.MoHaeServer.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "feedback")
public class Feedback {
    int id;

    String placeName;

    String location;

    String address;

    String summary;

    String imageUri;

    String description;

    List<String> likePeopleId;

    List<String> hatePeopleId;

    public Feedback(int id, String placeName, String location, String address, String summary,
                    String imageUri, String description, List<String> likePeopleId, List<String> hatePeopleId) {
        this.id = id;
        this.placeName = placeName;
        this.location = location;
        this.address = address;
        this.summary = summary;
        this.imageUri = imageUri;
        this.description = description;
        this.likePeopleId = likePeopleId;
        this.hatePeopleId = hatePeopleId;
    }

    public Feedback() {
        this.id = 0;
        this.placeName = "";
        this.location = "";
        this.address = "";
        this.summary = "";
        this.imageUri = "";
        this.description = "";
        this.likePeopleId = Collections.emptyList();
        this.hatePeopleId = Collections.emptyList();
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

    public List<String> getLikePeopleId() {
        return likePeopleId;
    }

    public void setLikePeopleId(List<String> likePeopleId) {
        this.likePeopleId = likePeopleId;
    }

    public List<String> getHatePeopleId() {
        return hatePeopleId;
    }

    public void setHatePeopleId(List<String> hatePeopleId) {
        this.hatePeopleId = hatePeopleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
