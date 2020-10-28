package com.group.api.web.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 기본 GROUP DTO
 * 이걸 그대로사용해도 되고,
 * 사용하고 싶은 상황에 맞게 새로 DTO를 만들어서 사용해도 됨
 * domain으로 변환하는과정은 일단은 구현가능한대로 구현하기!
 * */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GroupDto implements Serializable{
    private Long id;            // group key
    private String title;       // 제목
    private String category;    // 카테고리
    private String master;      // 그룹장ID
    private String description; // 설명글
}
