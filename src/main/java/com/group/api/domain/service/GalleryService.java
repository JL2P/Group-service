package com.group.api.domain.service;

import com.group.api.domain.Gallery;
import com.group.api.domain.Group;
import com.group.api.exception.GroupNotExistException;

import java.util.List;
import java.util.NoSuchElementException;


public interface GalleryService {
    public List<Gallery> getGalleries() throws NoSuchElementException;
    public Gallery getGallery(Group group) throws NoSuchElementException;
    public Gallery addGallery(Gallery gallery) throws GroupNotExistException;
    public Gallery modifyGallery(Gallery gallery) throws NoSuchElementException;
    public void deleteGallery(Long galleryId) throws NoSuchElementException;
    public boolean isExist(Long galleryId);

}