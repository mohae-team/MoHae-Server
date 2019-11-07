package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.*;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;
import com.mohaeyo.mohae.MoHaeServer.service.auth.Token;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.group.*;
import com.mohaeyo.mohae.MoHaeServer.model.response.ResponseGroupModel;
import com.mohaeyo.mohae.MoHaeServer.service.auth.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/mohae/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @Autowired
    AuthService authService;

    @PostMapping("/join")
    public ResponseEntity joinGroup(@RequestHeader String token, @RequestBody JoinGroupModel joinGroupModel) {
        int postId = joinGroupModel.getId();

        String id = new Token().verifyToken(token);

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
                    return ResponseEntity.ok(group.get());
                }
            } else {
                throw new ResetContentException("People max");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @DeleteMapping("/cancel")
    public ResponseEntity cancelGroup(@RequestHeader String token, @RequestBody CancelGroupModel cancelGroupModel) {
        int postId = cancelGroupModel.getId();

        String id = new Token().verifyToken(token);

        Optional<Group> group = groupService.findGroup(postId);

        if (group.isPresent()) {
            List<String> list = group.get().getPeopleId();

            if (list.contains(id)) {
                if (list.size() > 1) {
                    list.remove(id);
                    group.get().setPeopleId(list);
                    groupService.saveGroup(group.get());
                    return ResponseEntity.ok(group.get());
                } else {
                    groupService.removeGroup(group.get());
                    return ResponseEntity.ok(group.get());
                }
            } else {
                throw new NotFoundException("Not Joined");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestHeader String token, @RequestBody CreateGroupModel createGroupModel) {
        String id = new Token().verifyToken(token);

        List<String> peopleList = new ArrayList<>();
        peopleList.add(id);

        Group group = new Group(
                    new Object().hashCode(),
                    createGroupModel.getTitle(),
                    createGroupModel.getLocation(),
                    createGroupModel.getAddress(),
                    createGroupModel.getTerm(),
                    createGroupModel.getSummary(),
                    createGroupModel.getImageByteList(),
                    createGroupModel.getDescription(),
                    createGroupModel.getMaxCount(),
                    peopleList
                );

        groupService.insertGroup(group);

        return ResponseEntity.ok(group);
    }

    @GetMapping("/list")
    public ResponseEntity getListGroup(@RequestHeader String token) {
        String id = new Token().verifyToken(token);
        Optional<User> user = authService.getUserById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(
                    groupService.findAddressAllGroup(
                            user.get().getAddress()
                    )
            );
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @GetMapping("/detail")
    public ResponseEntity getGroup(@RequestHeader String token, @RequestBody GetGroupModel getGroupModel) {
        Optional<Group> group = groupService.findGroup(getGroupModel.getPostId());
        if (group.isPresent()) {
            List<String> peopleId = group.get().getPeopleId();
            if (peopleId.contains(new Token().verifyToken(token))) {
                return ResponseEntity.ok(new ResponseGroupModel(group.get(), true));
            } else {
                return ResponseEntity.ok(new ResponseGroupModel(group.get(), false));
            }
        } else {
            throw new NotFoundException("postId Not Found");
        }
    }


}
