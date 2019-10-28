package com.mohaeyo.mohae.MoHaeServer.service.qa;

import com.mohaeyo.mohae.MoHaeServer.model.entity.QA;
import com.mohaeyo.mohae.MoHaeServer.repository.QARepository;
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
    QARepository qaRepository;

    @Autowired
    MongoTemplate template;

    @Override
    public void saveQA(QA qa) {
        qaRepository.save(qa);
    }

    @Override
    public void insertQA(QA qa) {
        qaRepository.insert(qa);
    }

    @Override
    public Optional<QA> findQa(int id) {
        return qaRepository.findById(id);
    }

    @Override
    public List<QA> findAddressAllQA(String address) {
        return template.find(new Query(where("address").is(address)), QA.class);
    }
}
