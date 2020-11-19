package com.group.api.domain.service;

import com.group.api.domain.GroupTodo;
import com.group.api.domain.GroupTodoMember;
import com.group.api.exception.GroupTodoMemberExistException;
import com.group.api.exception.GroupTodoMemberNotExistException;

public interface GroupTodoMemberService {
    public GroupTodoMember addGroupTodoMember(String attenderId, GroupTodo groupTodo, String todoId) throws GroupTodoMemberExistException;
    public String checkAttendGroupTodoMember(String attenderId, GroupTodo groupTodo);
    public GroupTodoMember findGroupTodoMember(String attenderId, GroupTodo groupTodo) throws GroupTodoMemberNotExistException;
    public void removeGroupTodoMember(GroupTodoMember groupTodoMember);
}
