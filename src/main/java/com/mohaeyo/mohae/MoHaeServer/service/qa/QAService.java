package com.mohaeyo.mohae.MoHaeServer.service.qa;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Answer;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QAService {
    void saveQuestion(Question question);

    void insertQuestion(Question question);

    Optional<Question> findQuestion(int id);

    List<Question> findAddressAllQuestion(String address);

    void saveAnswer(Answer answer);

    void insertAnswer(Answer answer);

    Optional<Answer> findAnswer(int id);

    List<Answer> findAddressAllAnswer(int postId);
}
