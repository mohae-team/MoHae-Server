package com.mohaeyo.mohae.MoHaeServer.service.qa;

import com.mohaeyo.mohae.MoHaeServer.model.entity.QA;

import java.util.List;
import java.util.Optional;

public interface QAService {
    void saveQA(QA qa);

    void insertQA(QA qa);

    Optional<QA> findQa(int id);

    List<QA> findAddressAllQA(String address);
}
