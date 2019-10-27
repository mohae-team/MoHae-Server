package com.mohaeyo.mohae.MoHaeServer.service.place;


import com.mohaeyo.mohae.MoHaeServer.model.entity.Place;

import java.util.Optional;

public interface PlaceService {

    Optional<Place> findPlace(String location);

    void insertPlace(Place place);

    void savePlace(Place place);

    void removePlace(Place place);
}
