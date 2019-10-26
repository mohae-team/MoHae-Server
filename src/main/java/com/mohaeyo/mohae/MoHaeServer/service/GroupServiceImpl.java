package com.mohaeyo.mohae.MoHaeServer.service;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;
import com.mohaeyo.mohae.MoHaeServer.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    MongoTemplate template;

    @Override
    public Optional<Group> findGroup(int postId) {
        return groupRepository.findById(postId);
    }

    @Override
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void insertGroup(Group group) {
        groupRepository.insert(group);
    }

    @Override
    public List<Group> findAddressAllGroup(String address) {
        return template.find(new Query(where("address").is(address)), Group.class);
    }
}
