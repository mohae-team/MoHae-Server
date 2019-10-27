package com.mohaeyo.mohae.MoHaeServer.repository;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String> {

}
