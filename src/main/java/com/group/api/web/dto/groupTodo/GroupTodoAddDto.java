package com.group.api.web.dto.groupTodo;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupTodoAddDto {
    private String groupId;
    private String title;       // 제목
    private String imgUrl;      // 이미지 URL
    private String description; // 설명글
    private String category;    // 카테고리
    private String writer;      // 작성자ID

    public GroupTodo toDomain(Group group) {
        return GroupTodo.builder()
                .group(group)
                .title(this.title)
                .imgUrl(this.imgUrl)
                .description(this.description)
                .category(this.category)
                .writer(this.writer)
                .build();
    }
}
