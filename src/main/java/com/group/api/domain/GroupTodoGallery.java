package com.group.api.domain;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grouptodo_galleries")
public class GroupTodoGallery {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String filePath; //AWS에 저장된 파일 경로를 DB에 저장

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private GroupTodo todo;        // Group ID //group 객체를 받아오는데 자동으로 id를 보여준다.
}
