package com.group.api.web.dto.groupTodo;

import com.group.api.domain.Comment;
import com.group.api.domain.GroupTodo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentAddDto {
    private String text;        // 제목
    private String writer;      // 작성자ID

    public Comment toEntity(GroupTodo groupTodo) {
        return Comment.builder()
                .text(this.text)
                .writer(this.writer)
                .todo(groupTodo)
                .build();
    }
}
