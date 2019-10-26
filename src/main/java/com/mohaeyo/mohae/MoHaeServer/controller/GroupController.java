package com.mohaeyo.mohae.MoHaeServer.controller;

import com.mohaeyo.mohae.MoHaeServer.exception.*;
import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;
import com.mohaeyo.mohae.MoHaeServer.model.entity.User;
import com.mohaeyo.mohae.MoHaeServer.model.request.CreateGroupModel;
import com.mohaeyo.mohae.MoHaeServer.model.request.GetGroupModel;
import com.mohaeyo.mohae.MoHaeServer.model.request.GetListGroupModel;
import com.mohaeyo.mohae.MoHaeServer.model.request.JoinGroupModel;
import com.mohaeyo.mohae.MoHaeServer.model.response.ResponseGroupModel;
import com.mohaeyo.mohae.MoHaeServer.service.AuthService;
import com.mohaeyo.mohae.MoHaeServer.service.GroupService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
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
    public ResponseEntity joinGroup(@RequestBody JoinGroupModel joinGroupModel) {
        int postId = joinGroupModel.getId();
        String token = joinGroupModel.getToken();

        String id = verifyToken(token);

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
                    return ResponseEntity.ok().build();
                }
            } else {
                throw new ResetContentException("People max");
            }
        } else {
            throw new NotFoundException("Id Not Found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity createGroup(@RequestBody CreateGroupModel createGroupModel) {
        String id = verifyToken(createGroupModel.getToken());

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

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity getListGroup(@RequestBody GetListGroupModel getListGroupModel) {
        String id = verifyToken(getListGroupModel.getToken());
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
    public ResponseEntity getGroup(@RequestBody GetGroupModel getGroupModel) {
        Optional<Group> group = groupService.findGroup(getGroupModel.getPostId());
        if (group.isPresent()) {
            List<String> peopleId = group.get().getPeopleId();
            if (peopleId.contains(verifyToken(getGroupModel.getToken()))) {
                return ResponseEntity.ok(new ResponseGroupModel(group.get(), true));
            } else {
                return ResponseEntity.ok(new ResponseGroupModel(group.get(), false));
            }
        } else {
            throw new NotFoundException("postId Not Found");
        }
    }

    String verifyToken(String token) {
        String key = Base64.getEncoder().encodeToString(Base64.getEncoder().encodeToString("key".getBytes()).getBytes());
        JwtParser jwtParser = Jwts.parser();
        Claims claims = jwtParser
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        Date exp = claims.getExpiration();
        Date now = new Date();

        if (exp.after(now)) {
            return claims.get("data", String.class);
        } else {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }
}