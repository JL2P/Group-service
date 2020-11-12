package com.group.api.repository;

import com.group.api.domain.GroupTodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupTodoRepository extends JpaRepository<GroupTodo, Long> {
}
