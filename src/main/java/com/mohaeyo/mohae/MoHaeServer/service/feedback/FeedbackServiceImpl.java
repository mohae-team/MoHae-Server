package com.mohaeyo.mohae.MoHaeServer.service.feedback;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Feedback;
import com.mohaeyo.mohae.MoHaeServer.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    MongoTemplate template;

    @Override
    public void insertFeedback(Feedback feedback) {
        feedbackRepository.insert(feedback);
    }

    @Override
    public Optional<Feedback> findFeedback(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void removeFeedback(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }

    @Override
    public List<Feedback> findAddressAllFeedback(String address) {
        return template.find(new Query(where("address").is(address)), Feedback.class);
    }
}
