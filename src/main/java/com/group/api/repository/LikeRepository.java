package com.group.api.repository;

import com.group.api.domain.GroupTodo;
import com.group.api.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByTodoAndAccountId(GroupTodo todo, String accountId);
}
