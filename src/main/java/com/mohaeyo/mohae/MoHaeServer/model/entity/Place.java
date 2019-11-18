package com.mohaeyo.mohae.MoHaeServer.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "place")
public class Place {

    String id;

    String placeName;

    String description;

    List<String> likePeople;

    public Place(String placeName, String location, String description, List<String> likePeople) {
        this.placeName = placeName;
        this.id = location;
        this.description = description;
        this.likePeople = likePeople;
    }

    public Place() {
        this.placeName = "";
        this.id = "";
        this.description = "";
        this.likePeople = Collections.emptyList();
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLocation() {
        return id;
    }

    public void setLocation(String location) {
        this.id = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLikePeople() {
        return likePeople;
    }

    public void setLikePeople(List<String> likePeople) {
        this.likePeople = likePeople;
    }
}
