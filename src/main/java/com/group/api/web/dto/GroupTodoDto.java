package com.group.api.web.dto;

import com.group.api.domain.GroupTodo;
import lombok.*;

@Getter
@NoArgsConstructor
public class GroupTodoDto {
    private long todoId;            // Id
    private String imgUrl;      // 이미지 URL
    private String title;       // 제목
    private String description; // 설명글
    private String category;    // 카테고리
    private String writer;      // 작성자ID
    private String endTime;     // 마감일자
    private String groupAt;     // 그룹계획여부
    private int likePoint;      // 좋아요

    public GroupTodoDto(GroupTodo groupTodo) {
        this.todoId = groupTodo.getId();
        this.imgUrl = groupTodo.getImgUrl();
        this.title = groupTodo.getTitle();
        this.description = groupTodo.getDescription();
        this.category = groupTodo.getCategory();
        this.writer = groupTodo.getWriter();
        this.endTime = groupTodo.getEndTime();
        this.groupAt = groupTodo.getGroupAt();
        this.likePoint = groupTodo.getLikePoint();
    }
}
