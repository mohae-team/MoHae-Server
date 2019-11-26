package com.mohaeyo.mohae.MoHaeServer.service.qa;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Answer;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Question;
import com.mohaeyo.mohae.MoHaeServer.repository.AnswerRepository;
import com.mohaeyo.mohae.MoHaeServer.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class QAServiceImpl implements QAService {
    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    MongoTemplate template;

    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void insertQuestion(Question question) {
        questionRepository.insert(question);
    }

    @Override
    public Optional<Question> findQuestion(int id) {
        return questionRepository.findById(id);
    }

    @Override
    public List<Question> findAddressAllQuestion(String address) {
        return template.find(new Query(where("address").is(address)), Question.class);
    }

    @Override
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void insertAnswer(Answer answer) {
        answerRepository.insert(answer);
    }

    @Override
    public Optional<Answer> findAnswer(int id) {
        return answerRepository.findById(id);
    }

    @Override
    public List<Answer> findAddressAllAnswer(int postId) {
        return template.find(new Query(where("questionId").is(postId)), Answer.class);
    }
}
