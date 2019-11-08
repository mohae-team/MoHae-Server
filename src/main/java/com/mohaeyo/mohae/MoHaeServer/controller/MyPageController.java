package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.mypage.EditMyPageModel;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/mohae/mypage")
public class MyPageController {
    @Autowired
    AuthService authService;

    @PostMapping("/edit")
    public ResponseEntity editProfile(@RequestHeader String authorization, @RequestBody EditMyPageModel editMyPageModel) {
        Optional<User> user = authService.getUserById(
                new Token().verifyToken(authorization));

        if (user.isPresent()) {
            user.get().setUsername(editMyPageModel.getUsername());
            user.get().setImageByteList(editMyPageModel.getImageByteList());
            user.get().setAddress(editMyPageModel.getAddress());
            user.get().setDescription(editMyPageModel.getDescription());

            authService.saveUser(user.get());

            return ok(user.get());
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getProfile(@RequestHeader String authorization) {
        Optional<User> user = authService.getUserById(
                new Token().verifyToken(authorization));
        if (user.isPresent()) {
            return ok(user.get());
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }
}
