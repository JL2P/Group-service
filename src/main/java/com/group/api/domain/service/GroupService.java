package com.group.api.domain.service;

import com.group.api.domain.Group;
import com.group.api.exception.GroupNotExistException;

import java.util.List;
import java.util.NoSuchElementException;

public interface GroupService {


    public List<Group> getGroups() throws NoSuchElementException;
    public Group getGroup(Long groupId) throws NoSuchElementException;
    public Group addGroup(Group group) throws GroupNotExistException;
    public Group modifyGroup(Group group) throws NoSuchElementException;
    public void deleteGroup(Long groupId) throws NoSuchElementException;
    public boolean isExist(Long groupId);


}
