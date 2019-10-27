package com.mohaeyo.mohae.MoHaeServer.repository;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, Integer> {

}
