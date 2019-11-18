package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.AlreadyExistException;
import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Place;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.place.GetPlaceModel;
import com.mohaeyo.mohae.MoHaeServer.model.request.place.PostPlaceModel;
import com.mohaeyo.mohae.MoHaeServer.model.response.ResponsePlaceModel;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.service.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mohae/place")
public class PlaceController {
    @Autowired
    PlaceService placeService;

    @Autowired
    AuthService authService;

    @GetMapping("/get/{location}")
    public ResponseEntity getPlace(@RequestHeader("Authorization") String authorization, @PathVariable("location") String location) {
        Optional<Place> place = placeService.findPlace(location);
        if (place.isPresent()) {
            Optional<User> user = authService.getUserById(new Token().verifyToken(authorization));
            if (user.isPresent()) {

                if (place.get().getLikePeople().isEmpty()) {
                    return ResponseEntity.ok(
                            new ResponsePlaceModel(
                                    place.get().getPlaceName(),
                                    place.get().getLocation(),
                                    place.get().getDescription(),
                                    0,
                                    false));
                } else {
                    return ResponseEntity.ok(
                            new ResponsePlaceModel(
                                    place.get().getPlaceName(),
                                    place.get().getLocation(),
                                    place.get().getDescription(),
                                    place.get().getLikePeople().size(),
                                    place.get().getLikePeople().contains(user.get().getId())));
                }
            } else {
                throw new NotFoundException("Id Not Found");
            }
        } else {
            throw new NotFoundException("Place Not Found");
        }
    }

    @PostMapping("/like")
    public ResponseEntity postLikePlace(@RequestHeader("Authorization") String authorization,
                                        @RequestBody GetPlaceModel getPlaceModel) {
        Optional<Place> place = placeService.findPlace(getPlaceModel.getLocation());
        if (place.isPresent()) {
            Optional<User> user = authService.getUserById(new Token().verifyToken(authorization));
            if (user.isPresent()) {
                if (!(place.get().getLikePeople().contains(user.get().getId()))) {
                    List likePeopleList = place.get().getLikePeople();
                    likePeopleList.add(user.get().getId());
                    place.get().setLikePeople(likePeopleList);

                    placeService.savePlace(place.get());

                    return ResponseEntity.ok(
                            new ResponsePlaceModel(
                                    place.get().getPlaceName(),
                                    place.get().getLocation(),
                                    place.get().getDescription(),
                                    place.get().getLikePeople().size(),
                                    place.get().getLikePeople().contains(user.get().getId())));
                } else {
                    throw new AlreadyExistException("Already like");
                }
            } else {
                throw new NotFoundException("Id Not Found");
            }
        } else {
            throw new NotFoundException("Place Not Found");
        }
    }

    @PostMapping("/dislike")
    public ResponseEntity postDisLikePlace(@RequestHeader("Authorization") String authorization,
                                           @RequestBody GetPlaceModel getPlaceModel) {
        Optional<Place> place = placeService.findPlace(getPlaceModel.getLocation());
        if (place.isPresent()) {
            Optional<User> user = authService.getUserById(new Token().verifyToken(authorization));
            if (user.isPresent()) {
                if (place.get().getLikePeople().contains(user.get().getId())) {

                    List likePeopleList = place.get().getLikePeople();
                    likePeopleList.remove(user.get().getId());
                    place.get().setLikePeople(likePeopleList);

                    placeService.savePlace(place.get());

                    return ResponseEntity.ok(
                            new ResponsePlaceModel(
                                    place.get().getPlaceName(),
                                    place.get().getLocation(),
                                    place.get().getDescription(),
                                    place.get().getLikePeople().size(),
                                    place.get().getLikePeople().contains(user.get().getId())));
                } else {
                    throw new AlreadyExistException("Already dislike");
                }
            } else {
                throw new NotFoundException("Id Not Found");
            }
        } else {
            throw new NotFoundException("Place Not Found");
        }
    }

    @PostMapping("/post")
    public ResponseEntity postPlace(@RequestHeader("Authorization") String authorization,
                                    @RequestBody PostPlaceModel postPlaceModel) {
        Optional<Place> place = placeService.findPlace(postPlaceModel.getLocation());
        if (place.isPresent()) {
            Optional<User> user = authService.getUserById(new Token().verifyToken(authorization));

            if (user.isPresent()) {
                place.get().setDescription(
                        place.get().getDescription() + "\n" + postPlaceModel.getDescription()
                );
                placeService.savePlace(place.get());

                return ResponseEntity.ok(new ResponsePlaceModel(
                        place.get().getPlaceName(),
                        place.get().getLocation(),
                        place.get().getDescription(),
                        place.get().getLikePeople().size(),
                        place.get().getLikePeople().contains(user.get().getId())));
            } else {
                throw new NotFoundException("Id Not Found");
            }
        } else {
            Place newPlace = new Place(
                    postPlaceModel.getPlaceName(),
                    postPlaceModel.getLocation(),
                    postPlaceModel.getDescription(),
                    Collections.emptyList()
            );

            placeService.insertPlace(newPlace);

            return ResponseEntity.ok(
                    new ResponsePlaceModel(
                            newPlace.getPlaceName(),
                            newPlace.getLocation(),
                            newPlace.getDescription(),
                            0,
                            false));
        }
    }
}
