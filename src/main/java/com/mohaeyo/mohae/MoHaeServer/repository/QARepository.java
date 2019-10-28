package com.mohaeyo.mohae.MoHaeServer.repository;

import com.mohaeyo.mohae.MoHaeServer.model.entity.QA;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QARepository extends MongoRepository<QA, Integer> {
}
