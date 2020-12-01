package com.group.api.repository;

import com.group.api.domain.GroupTodo;
import com.group.api.domain.GroupTodoMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupTodoMemeberRepository extends JpaRepository<GroupTodoMember, Long> {
    Optional<GroupTodoMember> findByAttenderAndGrouptodo(String attender, GroupTodo groupTodo);
    Optional<GroupTodoMember> findByAttenderAndGrouptodoAndTodoId(String attender, GroupTodo groupTodo,String todoId);
    Optional<GroupTodoMember> findByTodoId(String todoId);
}
