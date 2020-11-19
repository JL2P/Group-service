package com.group.api.web.dto.groupTodo;

import com.group.api.domain.GroupTodoMember;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GroupTodoMemberDto {
    private long groupTodoMemberId;
    private String attender;
    private String todoId;
    private long grouptodoId;

    public GroupTodoMemberDto(GroupTodoMember groupTodoMember){
        this.groupTodoMemberId=groupTodoMember.getId();
        this.attender = groupTodoMember.getAttender();
        this.todoId = groupTodoMember.getTodoId();
        this.grouptodoId = groupTodoMember.getGrouptodo().getId();
    }
}
