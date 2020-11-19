package com.group.api.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "group_todo_member")
public class GroupTodoMember extends CommonDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String attender; // 참여자
    private String todoId;

    // GroupTodo ID
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_todo_id", nullable = false)
    private GroupTodo grouptodo;
}
