package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Place;
import com.mohaeyo.mohae.MoHaeServer.model.request.place.GetPlaceModel;
import com.mohaeyo.mohae.MoHaeServer.model.request.place.PostPlaceModel;
import com.mohaeyo.mohae.MoHaeServer.service.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/mohae/place")
public class PlaceController {
    @Autowired
    PlaceService placeService;

    @GetMapping("/get")
    public ResponseEntity getPlace(@RequestBody GetPlaceModel getPlaceModel) {
        Optional<Place> place = placeService.findPlace(getPlaceModel.getLocation());
        if (place.isPresent()) {
            return ResponseEntity.ok(place.get());
        } else {
            throw new NotFoundException("Place Not Found");
        }
    }

    @PostMapping("/post")
    public ResponseEntity postPlace(@RequestBody PostPlaceModel postPlaceModel) {
        Optional<Place> place = placeService.findPlace(postPlaceModel.getLocation());
        if (place.isPresent()) {
            place.get().setDescription(
                    place.get().getDescription() + "\n" + postPlaceModel.getDescription()
            );
            placeService.savePlace(place.get());

            return ResponseEntity.ok().build();
        } else {
            placeService.insertPlace(
                    new Place(
                        postPlaceModel.getLocation(),
                        postPlaceModel.getPlaceName(),
                        postPlaceModel.getDescription(),
                        Collections.emptyList()
                    )
            );

            return ResponseEntity.ok(place.get());
        }
    }
}
