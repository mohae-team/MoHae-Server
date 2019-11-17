package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.model.request.mypage.EditProfileModel;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity editProfile(@RequestHeader("Authorization") String authorization,
                                      @ModelAttribute EditProfileModel body) {
        Optional<User> user = authService.getUserById(new Token().verifyToken(authorization));

        storageService.store(body.getImageFile());

        if (user.isPresent()) {
            user.get().setUsername(body.getUsername());
            user.get().setAddress(body.getAddress());
            user.get().setImageUri("http://54.180.10.27:8080/mohae/image/" + body.getImageFile().getOriginalFilename());
            user.get().setDescription(body.getDescription());

            authService.saveUser(user.get());

            return ok(user.get());
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @RequestMapping(path = "/edit/image", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity editImageProfile(@RequestHeader("Authorization") String authorization,
                                           @RequestParam("imageFile") MultipartFile imageFile) {
        Optional<User> user = authService.getUserById(
                new Token().verifyToken(authorization));



        if (user.isPresent()) {


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
