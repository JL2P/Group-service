package com.group.api.web.dto.groupTodo;

import com.group.api.domain.GroupTodo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GroupTodoModifyDto {
    private long todoId;            // Id
    private String title;       // 제목
    private String description; // 설명글
    private String category;    // 카테고리

    public GroupTodoModifyDto(GroupTodo groupTodo) {
        this.todoId = groupTodo.getId();
        this.title = groupTodo.getTitle();
        this.description = groupTodo.getDescription();
        this.category = groupTodo.getCategory();
    }

    public GroupTodo toDomain(GroupTodo groupTodo) {
        return GroupTodo.builder()
                .id(this.todoId)
                .imgUrl(groupTodo.getImgUrl())
                .title(this.title)
                .category(this.category)
                .description(this.description)
                .writer(groupTodo.getWriter())
                .build();
    }
}
