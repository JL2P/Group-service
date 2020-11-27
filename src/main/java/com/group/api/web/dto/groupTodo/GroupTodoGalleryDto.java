package com.group.api.web.dto.groupTodo;

import com.group.api.domain.Gallery;
import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.GroupTodoGallery;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupTodoGalleryDto {
    private Long id;
    private String title;
    private String filePath;
    private Long todoId;

    public GroupTodoGalleryDto(GroupTodoGallery gallery){
        BeanUtils.copyProperties(gallery,this);
        this.todoId = gallery.getTodo().getId();
    }

    public GroupTodoGallery toDomain(GroupTodo groupTodo){
        GroupTodoGallery gallery = new GroupTodoGallery();
        BeanUtils.copyProperties(this,gallery);

        gallery.setTodo(groupTodo);
        return gallery;
    }
}
