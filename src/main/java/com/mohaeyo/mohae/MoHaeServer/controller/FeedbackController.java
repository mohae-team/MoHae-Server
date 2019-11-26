package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Feedback;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Place;
import com.mohaeyo.mohae.MoHaeServer.model.request.feedback.CreateFeedbackModel;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.response.ResponseFeedbackModel;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.feedback.FeedbackService;
import com.mohaeyo.mohae.MoHaeServer.service.place.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mohae/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @Autowired
    AuthService authService;

    @Autowired
    PlaceService placeService;

    @Autowired
    StorageService storageService;

    @PostMapping("/like/{id}")
    public ResponseEntity likeFeedback(@RequestHeader("Authorization") String authorization, @PathVariable("id") int postId) {

        String id = new Token().verifyToken(authorization);

        Optional<Feedback> feedback = feedbackService.findFeedback(postId);

        if (feedback.isPresent()) {
            List<String> hateList = feedback.get().getHatePeopleId();
            List<String> likeList = feedback.get().getLikePeopleId();

            if (likeList.size() <= 1000) {
                if (hateList.contains(id)) {
                    hateList.remove(id);
                    feedback.get().setHatePeopleId(hateList);
                }
                likeList.add(id);
                feedback.get().setLikePeopleId(likeList);
                feedbackService.saveFeedback(feedback.get());
            } else {
                feedbackService.removeFeedback(feedback.get());

                Optional<Place> place = placeService.findPlace(feedback.get().getLocation());
                if (place.isPresent()) {
                    placeService.removePlace(place.get());
                } else {
                    throw new NotFoundException("Place Not Found");
                }
            }
            return ResponseEntity.ok(new ResponseFeedbackModel(
                    feedback.get().getId(),
                    feedback.get().getPlaceName(),
                    feedback.get().getLocation(),
                    feedback.get().getAddress(),
                    feedback.get().getSummary(),
                    feedback.get().getImageUri(),
                    feedback.get().getDescription(),
                    feedback.get().getLikePeopleId().size(),
                    feedback.get().getHatePeopleId().size(),
                    true,
                    false));
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/hate/{id}")
    public ResponseEntity hateFeedback(@RequestHeader("Authorization") String authorization, @PathVariable("id") int postId) {
        String id = new Token().verifyToken(authorization);

        Optional<Feedback> feedback = feedbackService.findFeedback(postId);

        if (feedback.isPresent()) {
            List<String> hateList = feedback.get().getHatePeopleId();
            List<String> likeList = feedback.get().getLikePeopleId();

            if (hateList.size() <= 1000) {
                if (likeList.contains(id)) {
                    likeList.remove(id);
                    feedback.get().setLikePeopleId(likeList);
                }
                hateList.add(id);
                feedback.get().setHatePeopleId(hateList);
                feedbackService.saveFeedback(feedback.get());
            } else {
                feedbackService.removeFeedback(feedback.get());
            }
            return ResponseEntity.ok(new ResponseFeedbackModel(
                    feedback.get().getId(),
                    feedback.get().getPlaceName(),
                    feedback.get().getLocation(),
                    feedback.get().getAddress(),
                    feedback.get().getSummary(),
                    feedback.get().getImageUri(),
                    feedback.get().getDescription(),
                    feedback.get().getLikePeopleId().size(),
                    feedback.get().getHatePeopleId().size(),
                    false,
                    true));
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity createFeedback(@ModelAttribute CreateFeedbackModel body) {
        Optional<Place> place = placeService.findPlace(body.getLocation());
        if (place.isPresent()) {
            String placeName = place.get().getPlaceName();

            MultipartFile image = body.getImageFile();
            storageService.store(image);

            Feedback feedback = new Feedback(
                    new Object().hashCode(),
                    placeName,
                    body.getLocation(),
                    body.getAddress(),
                    body.getSummary(),
                    "http://54.180.10.27:8080/mohae/image/" + image.getOriginalFilename(),
                    body.getDescription(),
                    Collections.emptyList(),
                    Collections.emptyList()
            );

            feedbackService.insertFeedback(feedback);

            return ResponseEntity.ok(new ResponseFeedbackModel(
                    feedback.getId(),
                    feedback.getPlaceName(),
                    feedback.getLocation(),
                    feedback.getAddress(),
                    feedback.getSummary(),
                    feedback.getImageUri(),
                    feedback.getDescription(),
                    feedback.getLikePeopleId().size(),
                    feedback.getHatePeopleId().size(),
                    false,
                    false));
        } else {
            throw new NotFoundException("Location Not Found");
        }
    }

    @GetMapping("/list")
    public ResponseEntity getListFeedback(@RequestHeader("Authorization") String token) {
        String id = new Token().verifyToken(token);
        Optional<User> user = authService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(
                    feedbackService.findAddressAllFeedback(user.get().getAddress()).stream()
                            .map(
                                    feedback -> new ResponseFeedbackModel(
                                            feedback.getId(),
                                            feedback.getPlaceName(),
                                            feedback.getLocation(),
                                            feedback.getAddress(),
                                            feedback.getSummary(),
                                            feedback.getImageUri(),
                                            feedback.getDescription(),
                                            feedback.getLikePeopleId().size(),
                                            feedback.getHatePeopleId().size(),
                                            true,
                                            false)).toArray()
            );
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity getFeedback(@RequestHeader("Authorization") String token, @PathVariable("id") int id) {
        Optional<Feedback> feedback = feedbackService.findFeedback(id);
        if (feedback.isPresent()) {
            List<String> likePeopleId = feedback.get().getLikePeopleId();
            List<String> hatePeopleId = feedback.get().getHatePeopleId();
            if (likePeopleId.contains(new Token().verifyToken(token))) {
                return ResponseEntity.ok(new ResponseFeedbackModel(
                        feedback.get().getId(),
                        feedback.get().getPlaceName(),
                        feedback.get().getLocation(),
                        feedback.get().getAddress(),
                        feedback.get().getSummary(),
                        feedback.get().getImageUri(),
                        feedback.get().getDescription(),
                        feedback.get().getLikePeopleId().size(),
                        feedback.get().getHatePeopleId().size(),
                        true,
                        false));
            } else if (hatePeopleId.contains(new Token().verifyToken(token))) {
                return ResponseEntity.ok(new ResponseFeedbackModel(
                        feedback.get().getId(),
                        feedback.get().getPlaceName(),
                        feedback.get().getLocation(),
                        feedback.get().getAddress(),
                        feedback.get().getSummary(),
                        feedback.get().getImageUri(),
                        feedback.get().getDescription(),
                        feedback.get().getLikePeopleId().size(),
                        feedback.get().getHatePeopleId().size(),
                        false,
                        true));
            } else {
                return ResponseEntity.ok(new ResponseFeedbackModel(
                        feedback.get().getId(),
                        feedback.get().getPlaceName(),
                        feedback.get().getLocation(),
                        feedback.get().getAddress(),
                        feedback.get().getSummary(),
                        feedback.get().getImageUri(),
                        feedback.get().getDescription(),
                        feedback.get().getLikePeopleId().size(),
                        feedback.get().getHatePeopleId().size(),
                        false,
                        false));
            }
        } else {
            throw new NotFoundException("postId Not Found");
        }
    }
}
