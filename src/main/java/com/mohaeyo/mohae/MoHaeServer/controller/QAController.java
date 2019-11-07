package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Answer;
import com.mohaeyo.mohae.MoHaeServer.model.entity.QA;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.qa.*;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.qa.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mohae/qa")
public class QAController {
    @Autowired
    QAService qaService;

    @Autowired
    AuthService authService;

    @PostMapping("/question/create")
    public ResponseEntity createQa(@RequestHeader String token, @RequestBody CreateQAModel createQaModel) {
        String id = new Token().verifyToken(token);

        Optional<User> user = authService.getUserById(id);

        if (user.isPresent()) {
            QA qa = new QA(
                        new Object().hashCode(),
                        createQaModel.getTitle(),
                        user.get().getUsername(),
                        user.get().getAddress(),
                        createQaModel.getSummary(),
                        createQaModel.getImageByteList(),
                        createQaModel.getDescription(),
                        Collections.emptyList()
                    );

            qaService.insertQA(qa);

            return ResponseEntity.ok(qa);
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/answer/create")
    public ResponseEntity createAnswer(@RequestHeader String token, @RequestBody CreateAnswerModel createAnswerModel) {
        String id = new Token().verifyToken(token);

        Optional<User> user = authService.getUserById(id);
        Optional<QA> qa = qaService.findQa(createAnswerModel.getPostId());

        if (user.isPresent()) {
            if (qa.isPresent()) {
                List<Answer> answerList = qa.get().getAnswerList();
                answerList.add(
                        new Answer(
                                user.get().getUsername(),
                                createAnswerModel.getAnswer()));
                qa.get().setAnswerList(answerList);
                qaService.insertQA(qa.get());

                return ResponseEntity.ok(qa);
            } else {
                throw new NotFoundException("Qa Not Found");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/question/list")
    public ResponseEntity getListQuestion(@RequestHeader String token) {
        String id = new Token().verifyToken(token);
        Optional<User> user = authService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(qaService.findAddressAllQA(
                    user.get().getAddress()));
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/answer/list")
    public ResponseEntity getListAnswer(@RequestBody GetListAnswerModel getListAnswerModel) {
        int id = getListAnswerModel.getPostId();
        Optional<QA> qa = qaService.findQa(id);

        if (qa.isPresent()) {
            return ResponseEntity.ok(qa.get().getAnswerList());
        } else {
            throw new NotFoundException("QA Not Found");
        }
    }

    @GetMapping("/question/detail")
    public ResponseEntity getGroup(@RequestBody GetQAModel getQaModel) {
        Optional<QA> qa = qaService.findQa(getQaModel.getPostId());
        if (qa.isPresent()) {
            return ResponseEntity.ok(qa.get());
        } else {
            throw new NotFoundException("postId Not Found");
        }
    }
}
