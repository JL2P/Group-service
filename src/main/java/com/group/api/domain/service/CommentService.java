package com.group.api.domain.service;

import com.group.api.domain.Comment;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.SubComment;
import com.group.api.exception.CommentExistException;

import java.util.List;
import java.util.NoSuchElementException;

public interface CommentService {
    public List<Comment> getComments(GroupTodo todo) throws NoSuchElementException;

    public Comment getComment(Long commentId) throws NoSuchElementException;

    public Comment addComment(Comment comment) throws CommentExistException;

    public Comment modifyComment(Comment comment) throws NoSuchElementException;

    public void deleteComment(Long commentId) throws NoSuchElementException;


    public SubComment getSubComment(Long subCommentId) throws NoSuchElementException;

    public SubComment addSubComment(SubComment subComment) throws NoSuchElementException;

    public SubComment modifySubComment(SubComment subComment) throws NoSuchElementException;

    public void deleteSubComment(Long subCommentId) throws NoSuchElementException;
}
