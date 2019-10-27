package com.mohaeyo.mohae.MoHaeServer.service.feedback;


import com.mohaeyo.mohae.MoHaeServer.model.entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    void insertFeedback(Feedback feedback);

    Optional<Feedback> findFeedback(int id);

    void saveFeedback(Feedback feedback);

    void removeFeedback(Feedback feedback);

    List<Feedback> findAddressAllFeedback(String address);
}
