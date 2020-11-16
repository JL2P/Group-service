package com.group.api.web.dto.groupTodo;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.Member;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
public class GroupTodoDto {
    private long todoId;            // Id
    private String imgUrl;      // 이미지 URL
    private String title;       // 제목
    private String description; // 설명글
    private String category;    // 카테고리
    private String writer;      // 작성자ID
    private int likePoint;      // 좋아요 갯수
    private boolean likeState;  // 좋아요 했는지 체크
    private List<CommentDto> comments;
    private LocalDateTime created;
    private LocalDateTime modified;

    public GroupTodoDto(GroupTodo todo, boolean likeState) {
        this.todoId = todo.getId();
        this.imgUrl = todo.getImgUrl();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.category = todo.getCategory();
        this.writer = todo.getWriter();
        this.likePoint = todo.getLikes().size();
        this.likeState = likeState;
        this.comments = todo.getComments().stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
        this.created = todo.getCreated();
        this.modified = todo.getModified();
    }

}
