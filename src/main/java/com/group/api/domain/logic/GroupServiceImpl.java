package com.group.api.domain.logic;

import com.group.api.domain.Group;
import com.group.api.domain.service.GroupService;
import com.group.api.exception.GroupNotExistException;
import com.group.api.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;

    @Override
    public List<Group> getGroups() throws NoSuchElementException {
        List<Group> groups = repository.findAll();
        return groups;
    }

    @Override
    public Group getGroup(Long groupId) throws NoSuchElementException {
        //데이터가 하나도 없을 경우 빈 group객체 반환
        if(!isExist(groupId)) return new Group();

        return repository.findById(groupId).orElseThrow(()-> new NoSuchElementException());
    }

    @Override
    public Group addGroup(Group group) throws GroupNotExistException {
        if(isExist(group.getId()))throw new GroupNotExistException(group.getId().toString());

        return repository.save(group);
    }

    @Override
    public Group modifyGroup(Group group) throws NoSuchElementException {
        //DB에 todo가 존재하는지 확인
        if(!isExist(group.getId()))throw new NoSuchElementException(group.getId().toString());

        return repository.save(group);
    }

    @Override
    public void deleteGroup(Long groupId) throws NoSuchElementException {
        if(!isExist(groupId))throw new NoSuchElementException(groupId.toString());

        repository.deleteById(groupId);
    }

    @Override
    public boolean isExist(Long groupId) {
        Optional<Group> groupOpt = repository.findById(groupId);
        //Optional안에 todo객체가 존재하는 경우
        if(groupOpt.isPresent()) return true;

        //Optional안에 todo객체가 존재하지 않는 경우
        return false;
    }
}
