package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.*;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;
import com.mohaeyo.mohae.MoHaeServer.service.StorageService;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.group.*;
import com.mohaeyo.mohae.MoHaeServer.model.response.ResponseGroupModel;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/mohae/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @Autowired
    AuthService authService;

    @Autowired
    StorageService storageService;

    @PostMapping("/join/{id}")
    public ResponseEntity joinGroup(@RequestHeader("Authorization") String authorization, @PathVariable("id") int postId) {

        String id = new Token().verifyToken(authorization);

        Optional<Group> group = groupService.findGroup(postId);

        if (group.isPresent()) {
            List<String> list = group.get().getPeopleId();

            if (list.size() <= group.get().getMaxCount()) {
                if (list.contains(id)) {
                    throw new AlreadyExistException("Already Join");
                } else {
                    list.add(id);
                    group.get().setPeopleId(list);
                    groupService.saveGroup(group.get());
                    return ResponseEntity.ok(new ResponseGroupModel(
                            group.get().getId(),
                            group.get().getTitle(),
                            group.get().getLocation(),
                            group.get().getAddress(),
                            group.get().getTerm(),
                            group.get().getSummary(),
                            group.get().getImageUri(),
                            group.get().getDescription(),
                            group.get().getMaxCount(),
                            group.get().getPeopleId().size(),
                            true));
                }
            } else {
                throw new ResetContentException("People max");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity cancelGroup(@RequestHeader("Authorization") String authorization , @PathVariable("id") int postId) {
        String id = new Token().verifyToken(authorization);

        Optional<Group> group = groupService.findGroup(postId);

        if (group.isPresent()) {
            List<String> list = group.get().getPeopleId();

            if (list.contains(id)) {
                if (list.size() > 1) {
                    list.remove(id);
                    group.get().setPeopleId(list);
                    groupService.saveGroup(group.get());
                    return ResponseEntity.ok(new ResponseGroupModel(
                            group.get().getId(),
                            group.get().getTitle(),
                            group.get().getLocation(),
                            group.get().getAddress(),
                            group.get().getTerm(),
                            group.get().getSummary(),
                            group.get().getImageUri(),
                            group.get().getDescription(),
                            group.get().getMaxCount(),
                            group.get().getPeopleId().size(),
                            false));
                } else {
                    groupService.removeGroup(group.get());
                    return ResponseEntity.ok(new ResponseGroupModel(
                            group.get().getId(),
                            group.get().getTitle(),
                            group.get().getLocation(),
                            group.get().getAddress(),
                            group.get().getTerm(),
                            group.get().getSummary(),
                            group.get().getImageUri(),
                            group.get().getDescription(),
                            group.get().getMaxCount(),
                            group.get().getPeopleId().size(),
                            false));
                }
            } else {
                throw new NotFoundException("Not Joined");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestHeader("Authorization") String authorization,
                                      @ModelAttribute CreateGroupModel body) {
        String id = new Token().verifyToken(authorization);

        List<String> peopleList = new ArrayList<>();
        peopleList.add(id);

        MultipartFile image = body.getImageFile();
        storageService.store(image);

        Group group = new Group(
                    new Object().hashCode(),
                    body.getTitle(),
                    body.getLocation(),
                    body.getAddress(),
                    body.getTerm(),
                    body.getSummary(),
                "http://54.180.10.27:8080/mohae/image/" + image.getOriginalFilename(),
                    body.getDescription(),
                    50,
                    peopleList
                );

        groupService.insertGroup(group);

        return ResponseEntity.ok(new ResponseGroupModel(
                group.getId(),
                group.getTitle(),
                group.getLocation(),
                group.getAddress(),
                group.getTerm(),
                group.getSummary(),
                group.getImageUri(),
                group.getDescription(),
                group.getMaxCount(),
                group.getPeopleId().size(),
                true));
    }

    @GetMapping("/list")
    public ResponseEntity getListGroup(@RequestHeader("Authorization") String authorization) {
        String id = new Token().verifyToken(authorization);
        Optional<User> user = authService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(
                    groupService.findAddressAllGroup(
                            user.get().getAddress()).stream()
                            .map(
                                group -> new ResponseGroupModel(
                                        group.getId(),
                                        group.getTitle(),
                                        group.getLocation(),
                                        group.getAddress(),
                                        group.getTerm(),
                                        group.getSummary(),
                                        group.getImageUri(),
                                        group.getDescription(),
                                        group.getMaxCount(),
                                        group.getPeopleId().size(),
                                        group.getPeopleId().contains(user.get().getId()))).toArray()
            );
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity getGroup(@RequestHeader("Authorization") String authorization, @PathVariable("id") int id) {
        Optional<Group> group = groupService.findGroup(id);
        if (group.isPresent()) {
            List<String> peopleId = group.get().getPeopleId();
            if (peopleId.contains(new Token().verifyToken(authorization))) {
                return ResponseEntity.ok(new ResponseGroupModel(
                        group.get().getId(),
                        group.get().getTitle(),
                        group.get().getLocation(),
                        group.get().getAddress(),
                        group.get().getTerm(),
                        group.get().getSummary(),
                        group.get().getImageUri(),
                        group.get().getDescription(),
                        group.get().getMaxCount(),
                        peopleId.size(),
                        true));
            } else {
                return ResponseEntity.ok(new ResponseGroupModel(
                        group.get().getId(),
                        group.get().getTitle(),
                        group.get().getLocation(),
                        group.get().getAddress(),
                        group.get().getTerm(),
                        group.get().getSummary(),
                        group.get().getImageUri(),
                        group.get().getDescription(),
                        group.get().getMaxCount(),
                        peopleId.size(),
                        false));
            }
        } else {
            throw new NotFoundException("postId Not Found");
        }
    }


}
