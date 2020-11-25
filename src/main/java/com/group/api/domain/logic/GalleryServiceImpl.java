package com.group.api.domain.logic;

import com.group.api.domain.Gallery;
import com.group.api.domain.Group;
import com.group.api.domain.Member;
import com.group.api.domain.service.GalleryService;
import com.group.api.exception.GroupNotExistException;
import com.group.api.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

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
}
