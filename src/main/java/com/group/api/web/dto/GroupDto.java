package com.group.api.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.group.api.web.dto.groupTodo.GroupTodoDto;
import org.springframework.beans.BeanUtils;

import com.group.api.domain.Group;

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
    private String imgUrl;      // 이미지 URL
    private List<MemberDto> members;
    private List<GroupTodoDto> groupTodos;
    
    public GroupDto(Group group){
    	BeanUtils.copyProperties(group, this);
    	//group안에 있는 memebers(리스트)안의 member전체를 DTO로 변환하기위하는 부분
    	this.members = group.getMembers().stream().map(member -> new MemberDto(member)).collect(Collectors.toList());
    	//group안에 있는 groupTodos(리스트)안의 groupTodo전체를 DTO로 변환하기위하는 부분
        //groupTodos는 필요하면 세팅해주도록 변경
        this.groupTodos= new ArrayList<>();
//        this.groupTodos = group.getGroupTodos().stream().map(groupTodo -> new GroupTodoDto(groupTodo)).collect(Collectors.toList());
    }
    
    public Group toDomain() {
    	Group group = new Group();
    	BeanUtils.copyProperties(this,group);
    	return group;
    }
}
