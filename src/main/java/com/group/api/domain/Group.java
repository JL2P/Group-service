package com.group.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;       // 제목
    private String category;    // 카테고리
    private String master;      // 그룹장ID
    private String description; // 설명글
    private String imgUrl;      // 이미지 URL
    private String openAt;      // 그룹 공개여부
    
    //member
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<Member>();

    //groupTodo
    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<GroupTodo> groupTodos = new ArrayList<GroupTodo>();

}
