package com.group.api.web;

import com.group.api.domain.Gallery;
import com.group.api.domain.service.GalleryService;
import com.group.api.domain.service.S3Service;
import com.group.api.web.dto.GalleryDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = {"6. Grallery"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class GalleryController {
    private final S3Service s3Service;
    private final GalleryService galleryService;

    @GetMapping("gallery")
    public String dispWrite() {
        return "/gallery";
    }

    @PostMapping("gallery")
    public GalleryDto execWrite(@RequestParam("file") MultipartFile file) throws IOException {

        String imgPath = s3Service.upload(file);
        String filename= file.getOriginalFilename();
        //생성자와 같지만 더 명확하게 보여줌 //데이터를 받아오는 건 엔티티 객체로 저장
        Gallery newGallery = Gallery.builder()
                .title(filename)
                .filePath(imgPath)
                .build();

        //엔티티를 DTO로 변환해서 클라이언트에 반환
        return new GalleryDto(galleryService.savePost(newGallery));



//        return GalleryDto.builder()
//                .id(100l)
//                .title(filename)
//                .filePath(imgPath)
//                .build();
    }
}
