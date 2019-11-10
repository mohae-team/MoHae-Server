package com.mohaeyo.mohae.MoHaeServer.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "user")
public class User {
    String id;

    String password;

    String username;

    String imageUri;

    String address;

    String description;

    public User(String id, String password, String username, String imageUri, String address, String description) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.imageUri = imageUri;
        this.address = address;
        this.description = description;
    }

    public User() {
        this.id = "";
        this.password = "";
        this.username = "";
        this.imageUri = "";
        this.address = "";
        this.description = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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
