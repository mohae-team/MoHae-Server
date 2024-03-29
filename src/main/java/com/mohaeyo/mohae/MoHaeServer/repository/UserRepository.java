package com.mohaeyo.mohae.MoHaeServer.repository;

import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}