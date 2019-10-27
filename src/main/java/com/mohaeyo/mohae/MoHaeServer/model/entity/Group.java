package com.mohaeyo.mohae.MoHaeServer.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "group")
public class Group {
    int id;

    String title;

    String location;

    String address;

    String term;

    String summary;

    List<Byte> imageByteList;

    String description;

    int maxCount;

    List<String> peopleId;

    public Group(int id, String title, String location, String address, String term, String summary,
                 List<Byte> imageByteList, String description, int maxCount, List<String> peopleId) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.address = address;
        this.term = term;
        this.summary = summary;
        this.imageByteList = imageByteList;
        this.description = description;
        this.maxCount = maxCount;
        this.peopleId = peopleId;
    }

    public Group() {
        this.id = 0;
        this.title = "";
        this.location = "";
        this.address = "";
        this.term = "";
        this.summary = "";
        this.imageByteList = Collections.emptyList();
        this.description = "";
        this.maxCount = 0;
        this.peopleId = Collections.emptyList();
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

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public List<String> getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(List<String> peopleId) {
        this.peopleId = peopleId;
    }
}
