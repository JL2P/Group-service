package com.group.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;        // Group ID //group 객체를 받아오는데 자동으로 id를 보여준다.

    private String accountId;   // 멤버 ID
    private String confirm;     // (승인 Y / 미승인 N) 승인여부
    private String manager; //관리자
}
