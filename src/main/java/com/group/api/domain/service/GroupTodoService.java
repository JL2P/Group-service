package com.group.api.domain.service;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.exception.GroupNotExistException;

import java.util.List;
import java.util.NoSuchElementException;

public interface GroupTodoService {
    public List<GroupTodo> getAllTodos() throws NoSuchElementException;

    public List<GroupTodo> getTodos(Group group) throws NoSuchElementException;

    public GroupTodo getTodo(Long todoId) throws NoSuchElementException;

    public GroupTodo addTodo(GroupTodo todo) throws GroupNotExistException;

    public GroupTodo modifyTodo(GroupTodo todo) throws NoSuchElementException;

    public void deleteTodo(Long todoId) throws NoSuchElementException;

    public boolean isExist(Long todoId);
}
