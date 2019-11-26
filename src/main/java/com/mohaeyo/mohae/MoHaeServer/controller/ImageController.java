package com.mohaeyo.mohae.MoHaeServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mohae")
public class ImageController {

    @Autowired
    ResourceLoader resourceLoader;

    private final String UPLOAD_ROOT = "/home/ubuntu/MoHae-Server/travis/images/";

    @GetMapping(value = "/image/{imageUri}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity getImage(@PathVariable("imageUri") String filename) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + UPLOAD_ROOT + filename));
    }
}
