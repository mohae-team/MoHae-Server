package com.mohaeyo.mohae.MoHaeServer.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
@Entity
public class User {
    @Id
    String id;

    @Column(name = "password")
    String password;

    @Column(name = "username")
    String username;

    @Column(name = "imageString")
    String imageString;

    @Column(name = "address")
    String address;

    @Column(name = "description")
    String description;

    public User(String id, String password, String username, String imageString, String address, String description) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.imageString = imageString;
        this.address = address;
        this.description = description;
    }

    public User() {
        this.id = "";
        this.password = "";
        this.username = "";
        this.imageString = "";
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

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
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
