package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.NotFoundException;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
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

    @PostMapping("/edit")
    public ResponseEntity editProfile(@RequestHeader("Authorization") String authorization,
                                      @RequestParam("username") String username,
                                      @RequestParam("address") String address,
                                      @RequestParam("description") String description,
                                      @RequestParam("imageFile") MultipartFile imageFile) {
        Optional<User> user = authService.getUserById(
                new Token().verifyToken(authorization));

        storageService.store(imageFile);

        if (user.isPresent()) {
            user.get().setUsername(username);
            user.get().setAddress(address);
            user.get().setImageUri("http://54.180.10.27:8080/image/" + imageFile.getOriginalFilename());
            user.get().setDescription(description);

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
