package com.group.api.repository;

import com.group.api.domain.Gallery;
import com.group.api.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    Optional<Gallery> findByGroup(Group group);
}