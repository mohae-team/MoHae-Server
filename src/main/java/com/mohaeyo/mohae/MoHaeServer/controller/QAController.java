package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Answer;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Question;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.qa.*;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.qa.QAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mohae/qa")
public class QAController {
    @Autowired
    QAService qaService;

    @Autowired
    AuthService authService;

    @Autowired
    StorageService storageService;

    @PostMapping("/question/create")
    public ResponseEntity createQa(@RequestHeader("Authorization") String authorization,
                                   @ModelAttribute CreateQAModel body) {
        String id = new Token().verifyToken(authorization);

        Optional<User> user = authService.getUserById(id);

        MultipartFile image = body.getImageFile();
        storageService.store(image);

        if (user.isPresent()) {
            Question question = new Question(
                        new Object().hashCode(),
                        body.getTitle(),
                        user.get().getUsername(),
                        user.get().getAddress(),
                        body.getSummary(),
                    "http://54.180.10.27:8080/mohae/image/" + image.getOriginalFilename(),
                        body.getDescription()
                    );

            qaService.insertQuestion(question);

            return ResponseEntity.ok(question);
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/answer/create")
    public ResponseEntity createAnswer(@RequestHeader("Authorization") String authorization, @RequestBody CreateAnswerModel createAnswerModel) {
        String id = new Token().verifyToken(authorization);

        Optional<User> user = authService.getUserById(id);
        Optional<Question> question = qaService.findQuestion(createAnswerModel.getPostId());

        if (user.isPresent()) {
            if (question.isPresent()) {
                Answer answer = new Answer(
                        new Object().hashCode(),
                        createAnswerModel.getPostId(),
                        user.get().getUsername(),
                        createAnswerModel.getAnswer());
                qaService.saveAnswer(answer);

                return ResponseEntity.ok(answer);
            } else {
                throw new NotFoundException("Qa Not Found");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/question/list")
    public ResponseEntity getListQuestion(@RequestHeader("Authorization") String authorization) {
        String id = new Token().verifyToken(authorization);
        Optional<User> user = authService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(qaService.findAddressAllQuestion(user.get().getAddress()));
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/answer/list/{id}")
    public ResponseEntity getListAnswer(@PathVariable("id") int id) {
        List<Answer> answerList = qaService.findAddressAllAnswer(id);

        return ResponseEntity.ok(answerList);
    }

    @GetMapping("/question/detail/{id}")
    public ResponseEntity getGroup(@PathVariable("id") int id) {
        Optional<Question> qa = qaService.findQuestion(id);
        if (qa.isPresent()) {
            return ResponseEntity.ok(qa.get());
        } else {
            throw new NotFoundException("postId Not Found");
        }
    }
}
