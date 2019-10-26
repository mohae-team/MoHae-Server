package com.mohaeyo.mohae.MoHaeServer.service;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    Optional<Group> findGroup(int postId);

    void saveGroup(Group group);

    void insertGroup(Group group);

    List<Group> findAddressAllGroup(String address);
}
