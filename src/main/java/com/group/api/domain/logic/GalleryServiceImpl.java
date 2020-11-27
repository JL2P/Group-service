package com.group.api.domain.logic;

import com.group.api.domain.*;
import com.group.api.domain.service.GalleryService;
import com.group.api.exception.GroupNotExistException;
import com.group.api.repository.GalleryRepository;
import com.group.api.repository.GroupTodoGalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final GroupTodoGalleryRepository groupTodoGalleryRepository;

    @Override
    public List<Gallery> getGalleries() throws NoSuchElementException {
        return null;
    }

    @Override
    public Gallery getGallery(Group group) throws NoSuchElementException {
        return galleryRepository.findByGroup(group).orElse(null);
    }

    @Override
    public Gallery addGallery(Gallery gallery) throws GroupNotExistException {
        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery modifyGallery(Gallery gallery) throws NoSuchElementException {
        return null;
    }

    @Override
    public void deleteGallery(Long galleryId) throws NoSuchElementException {

    }

    @Override
    public boolean isExist(Long galleryId) {
        return false;
    }

    // GroupTodoGalleries

    @Override
    public List<GroupTodoGallery> getGroupTodoGalleries() throws NoSuchElementException {
        return null;
    }

    @Override
    public GroupTodoGallery getGroupTodoGallery(GroupTodo groupTodo) throws NoSuchElementException {
        return groupTodoGalleryRepository.findByTodo(groupTodo).orElse(null);
    }

    @Override
    public GroupTodoGallery addGroupTodoGallery(GroupTodoGallery gallery) throws GroupNotExistException {
        return groupTodoGalleryRepository.save(gallery);
    }

    @Override
    public GroupTodoGallery modifyGroupTodoGallery(GroupTodoGallery gallery) throws NoSuchElementException {
        return null;
    }

    @Override
    public void deleteGroupTodoGallery(Long galleryId) throws NoSuchElementException {

    }

    @Override
    public boolean isGroupTodoExist(Long galleryId) {
        return false;
    }
}
