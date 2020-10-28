package com.group.api.web.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.group.api.domain.Group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GroupCreateDto implements Serializable{
	private Long id;            // group key
    private String title;       // 제목
    private String category;    // 카테고리
    private String master;      // 그룹장ID
    private String description; // 설명글
    
    public GroupCreateDto(Group group){
    	BeanUtils.copyProperties(group, this);
    }
    
    public Group toDomain() {
    	Group group = new Group();
    	BeanUtils.copyProperties(this,group);
    	return group;
    }
}
