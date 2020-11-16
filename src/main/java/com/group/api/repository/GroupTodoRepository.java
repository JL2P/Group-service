package com.group.api.repository;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupTodoRepository extends JpaRepository<GroupTodo, Long> {
    List<GroupTodo> findByGroup(Group group, Sort sort);
}
