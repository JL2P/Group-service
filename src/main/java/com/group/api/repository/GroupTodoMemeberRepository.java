package com.group.api.repository;

import com.group.api.domain.GroupTodoMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupTodoMemeberRepository extends JpaRepository<GroupTodoMember, Long> {
}
