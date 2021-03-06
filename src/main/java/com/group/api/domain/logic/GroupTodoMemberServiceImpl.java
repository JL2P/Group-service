package com.group.api.domain.logic;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.GroupTodoMember;
import com.group.api.domain.service.GroupTodoMemberService;
import com.group.api.exception.GroupTodoMemberExistException;
import com.group.api.exception.GroupTodoMemberNotExistException;
import com.group.api.repository.GroupTodoMemeberRepository;
import com.group.api.repository.GroupTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class GroupTodoMemberServiceImpl implements GroupTodoMemberService {
    private final GroupTodoMemeberRepository groupTodoMemeberRepository;
    private final GroupTodoRepository groupTodoRepository;

    @Override
    public GroupTodoMember addGroupTodoMember(String attenderId, GroupTodo groupTodo, String todoId) throws GroupTodoMemberExistException {
        // 이미 참여중인지 체크
        System.out.println(groupTodoMemeberRepository.findByAttenderAndGrouptodoAndTodoId(attenderId,groupTodo,todoId).isEmpty());
        if(!groupTodoMemeberRepository.findByAttenderAndGrouptodoAndTodoId(attenderId,groupTodo,todoId).isEmpty()) {
            throw new GroupTodoMemberExistException("이미 참여중인 Todo입니다.");
        }

        GroupTodoMember groupTodoMember = GroupTodoMember.builder().attender(attenderId).grouptodo(groupTodo).todoId(todoId).build();
        return groupTodoMemeberRepository.save(groupTodoMember);
    }

    public String checkAttendGroupTodoMember(String attenderId, GroupTodo groupTodo){
        if(groupTodoMemeberRepository.findByAttenderAndGrouptodo(attenderId, groupTodo).isEmpty()) return "NONE";
        return "ATTEND";
    }

    @Override
    public GroupTodoMember findGroupTodoMember(String attenderId, GroupTodo groupTodo) throws GroupTodoMemberNotExistException {
        return groupTodoMemeberRepository.findByAttenderAndGrouptodo(attenderId, groupTodo).orElseThrow(()-> new GroupTodoMemberNotExistException("GroupTodoMember Not Exist"));
    }

    @Override
    public Group findTodoGroup(String todoId) {
        GroupTodoMember groupTodoMember = groupTodoMemeberRepository.findByTodoId(todoId).orElseThrow(()->new NoSuchElementException());
        GroupTodo groupTodo = groupTodoRepository.findById(groupTodoMember.getGrouptodo().getId()).orElseThrow(()->new NoSuchElementException());
        return groupTodo.getGroup();
    }

    @Override
    public void removeGroupTodoMember(GroupTodoMember groupTodoMember) {
        groupTodoMemeberRepository.delete(groupTodoMember);
    }
}
