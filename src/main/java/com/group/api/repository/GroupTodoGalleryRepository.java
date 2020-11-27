package com.group.api.repository;

import com.group.api.domain.GroupTodo;
import com.group.api.domain.GroupTodoGallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupTodoGalleryRepository extends JpaRepository<GroupTodoGallery, Long> {
    Optional<GroupTodoGallery> findByTodo(GroupTodo groupTodo);
}
