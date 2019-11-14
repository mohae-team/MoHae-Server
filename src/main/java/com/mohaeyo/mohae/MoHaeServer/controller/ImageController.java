package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.model.request.group.JoinGroupModel;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mohae")
public class ImageController {

    @Autowired
    ResourceLoader resourceLoader;

    private final String UPLOAD_ROOT = "src/main/resources/";

    @GetMapping(value = "/image/{imageUri}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity getImage(@PathVariable("imageUri") String filename) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + UPLOAD_ROOT + filename));
    }
}
