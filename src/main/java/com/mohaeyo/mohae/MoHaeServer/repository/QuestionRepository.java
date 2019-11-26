package com.mohaeyo.mohae.MoHaeServer.repository;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Integer> {
}
