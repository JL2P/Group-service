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
	private String openAt;      // 그룹 공개 여부

    public GroupModifyDto(Group group){
    	BeanUtils.copyProperties(group, this);
    }

	public Group toEntity(Group group){
		group.setId(this.id);
		group.setImgUrl(group.getImgUrl());
		group.setTitle(this.title);
		group.setCategory(group.getCategory());
		group.setMaster(group.getMaster());
		group.setDescription(this.description);
		group.setOpenAt(this.openAt);

		return group;
	}
}
