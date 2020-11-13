package com.group.api.web.dto;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.Member;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Getter
@NoArgsConstructor
public class GroupTodoDto {
    private Long todoId;            // Id
    private Long groupId;
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
        this.groupId = groupTodo.getGroup().getId();
        this.imgUrl = groupTodo.getImgUrl();
        this.title = groupTodo.getTitle();
        this.description = groupTodo.getDescription();
        this.category = groupTodo.getCategory();
        this.writer = groupTodo.getWriter();
        this.endTime = groupTodo.getEndTime();
        this.groupAt = groupTodo.getGroupAt();
        this.likePoint = groupTodo.getLikePoint();
    }
    public GroupTodo toDomain(Group group) {
        GroupTodo groupTodo = new GroupTodo();
        BeanUtils.copyProperties(this,groupTodo);
        //GroupTodo를 만들려고하니, groupTodo 도메인에 Group이 연관관계로 있어서 반드시 넣어줘야함
        //그래서 controller에서 받아온 groupId를 이용해서 GroupService를 통해 Group객체를 받아서 넣어주는 부분
        groupTodo.setGroup(group);
        return groupTodo;
    }
}
