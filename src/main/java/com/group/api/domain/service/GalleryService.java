package com.group.api.domain.service;

import com.group.api.domain.Gallery;
import com.group.api.repository.GalleryRepository;
import com.group.api.web.dto.GalleryDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;

    public Gallery savePost(Gallery gallery) {
        return galleryRepository.save(gallery);
    }
}