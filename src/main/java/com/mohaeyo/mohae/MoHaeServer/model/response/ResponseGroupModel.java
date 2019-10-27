package com.mohaeyo.mohae.MoHaeServer.model.response;

import com.mohaeyo.mohae.MoHaeServer.model.entity.Group;

public class ResponseGroupModel {
    Group group;
    boolean isJoin;

    public ResponseGroupModel(Group group, boolean isJoin) {
        this.group = group;
        this.isJoin = isJoin;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }
}
