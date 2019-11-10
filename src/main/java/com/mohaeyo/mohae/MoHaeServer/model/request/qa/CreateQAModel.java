package com.mohaeyo.mohae.MoHaeServer.model.request.qa;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreateQAModel {

    String title;

    String summary;

    String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
