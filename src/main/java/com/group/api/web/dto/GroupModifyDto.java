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
public class GroupModifyDto implements Serializable{
	
	private Long id;
	private String title;       // 제목
    private String description; // 설명글
    
    public GroupModifyDto(Group group){
    	BeanUtils.copyProperties(group, this);
    }
    
    public Group toDomain() {
    	Group group = new Group();
    	BeanUtils.copyProperties(this,group);
    	return group;
    }
}
