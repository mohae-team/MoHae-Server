package com.mohaeyo.mohae.MoHaeServer.service;

import com.mohaeyo.mohae.MoHaeServer.model.entity.User;

import java.util.Optional;

public interface AuthService {
    void saveUser(User user);

    Optional<User> getUserById(String id);
}
