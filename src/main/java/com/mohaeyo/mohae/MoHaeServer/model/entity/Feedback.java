package com.mohaeyo.mohae.MoHaeServer.model.entity;

import java.util.List;

public class Feedback {
    int id;

    String placeName;

    String address;

    String summary;

    List<Byte> imageByteList;

    String description;

    int likeCount;

    int hateCount;
}
