package com.group.api.web;

import com.group.api.domain.Gallery;
import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.GroupTodoGallery;
import com.group.api.domain.service.GalleryService;
import com.group.api.domain.service.GroupService;
import com.group.api.domain.service.GroupTodoService;
import com.group.api.domain.service.S3Service;
import com.group.api.web.dto.GalleryDto;
import com.group.api.web.dto.groupTodo.GroupTodoGalleryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
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
    private final GroupService groupService;
    private final GroupTodoService groupTodoService;

    @Value("${cloud.aws.s3.url}")
    private String S3_url;

    @GetMapping("gallery")
    public String dispWrite() {
        return "/gallery";
    }

    @ApiOperation(value = "filePath 추가", notes = "filePath 데이터를 받아온다.")
    @PostMapping("{groupId}/gallery")
    public GalleryDto execWrite(@RequestParam("file") MultipartFile file, @PathVariable Long groupId) throws IOException {
        Group group = groupService.getGroup(groupId);
        //그룹에 맞는 갤러리가 있는지
        Gallery gallery = galleryService.getGallery(group);
        String filePath = null;

        if(gallery != null ){
            filePath = gallery.getTitle();
        }

        String fileName = s3Service.upload(filePath,file);
//        galleryService.getGallery().getFilePath()
//        String filename= file.getOriginalFilename();
        //생성자와 같지만 더 명확하게 보여줌 //데이터를 받아오는 건 엔티티 객체로 저장
        Gallery newGallery = Gallery.builder()
                .title(fileName)
                .filePath(S3_url+fileName)
                .group(group)
                .build();
        //겔러리가 이미 있는 건 UPDATE
        //겔러리의 ID를 취득하여 newGallery에 넣어줌
        //save시에 ID 값이 있으면 업데이트가 됨
        if(gallery != null) newGallery.setId((gallery.getId()));

        //엔티티를 DTO로 변환해서 클라이언트에 반환
        return new GalleryDto(galleryService.addGallery(newGallery));

    }

    @ApiOperation(value = "filePath 추가", notes = "filePath 데이터를 받아온다.")
    @PostMapping("{groupId}/todos/{groupTodoId}")
    public GroupTodoGalleryDto execTodoWrite(@RequestParam("file") MultipartFile file, @PathVariable Long groupId, @PathVariable Long groupTodoId) throws IOException {
        GroupTodo groupTodo = groupTodoService.getTodo(groupTodoId);

        //그룹Todo에 맞는 갤러리가 있는지
        GroupTodoGallery gallery = galleryService.getGroupTodoGallery(groupTodo);

        String filePath = null;

        if(gallery != null ){
            filePath = gallery.getTitle();
        }

        String fileName = s3Service.upload(filePath, file);
//        galleryService.getGallery().getFilePath()
//        String filename= file.getOriginalFilename();
        //생성자와 같지만 더 명확하게 보여줌 //데이터를 받아오는 건 엔티티 객체로 저장
        GroupTodoGallery newGallery = GroupTodoGallery.builder()
                .title(fileName)
                .filePath(S3_url+fileName)
                .todo(groupTodo)
                .build();
        //겔러리가 이미 있는 건 UPDATE
        //겔러리의 ID를 취득하여 newGallery에 넣어줌
        //save시에 ID 값이 있으면 업데이트가 됨
        if(gallery != null) newGallery.setId((gallery.getId()));

        //엔티티를 DTO로 변환해서 클라이언트에 반환
        return new GroupTodoGalleryDto(galleryService.addGroupTodoGallery(newGallery));

    }
}

