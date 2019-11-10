package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.mypage.EditMyPageModel;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/mohae/mypage")
public class MyPageController {
    @Autowired
    AuthService authService;

    @Autowired
    StorageService storageService;

    @RequestMapping(path = "/edit", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity editProfile(@RequestHeader("Authorization") String authorization,
                                      @RequestParam("image") MultipartFile imageFile,
                                      @RequestBody EditMyPageModel editMyPageModel) {
        Optional<User> user = authService.getUserById(
                new Token().verifyToken(authorization));

        MultipartFile image = imageFile;
        storageService.store(image);

        if (user.isPresent()) {
            user.get().setUsername(editMyPageModel.getUsername());
            user.get().setAddress(editMyPageModel.getAddress());
            user.get().setImageUri("http://54.180.10.27:8080/" + "image/" + image.getOriginalFilename());
            user.get().setImageUri(image.getOriginalFilename());
            user.get().setDescription(editMyPageModel.getDescription());

            authService.saveUser(user.get());

            return ok(user.get());
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/get")
    public ResponseEntity getProfile(@RequestHeader("Authorization") String authorization) {
        Optional<User> user = authService.getUserById(
                new Token().verifyToken(authorization));
        if (user.isPresent()) {
            return ok(user.get());
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }
}
