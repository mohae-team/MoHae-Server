package com.mohaeyo.mohae.MoHaeServer.service.place;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Place;
import com.mohaeyo.mohae.MoHaeServer.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    PlaceRepository placeRepository;

    @Override
    public Optional<Place> findPlace(String location) {
        return placeRepository.findById(location);
    }

    @Override
    public void savePlace(Place place) {
        placeRepository.save(place);
    }

    @Override
    public void insertPlace(Place place) {
        placeRepository.insert(place);
    }

    @Override
    public void removePlace(Place place) {
        placeRepository.delete(place);
    }
}
