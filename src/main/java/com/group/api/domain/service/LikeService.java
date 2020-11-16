package com.group.api.domain.service;

import com.group.api.domain.GroupTodo;

import java.util.NoSuchElementException;

public interface LikeService {
    public void addLike(GroupTodo groupTodo, String accountId);
    public void removeLike(GroupTodo groupTodo,String accountId) throws NoSuchElementException;
    public boolean checkLiked(GroupTodo groupTodo, String accountId);
}
