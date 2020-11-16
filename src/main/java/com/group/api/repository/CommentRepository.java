package com.group.api.repository;

import com.group.api.domain.Comment;
import com.group.api.domain.GroupTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByTodo(GroupTodo todo);
}
