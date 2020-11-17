package com.group.api.web.dto;

import com.group.api.domain.Group;
import lombok.*;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GroupTransferDto {
    private Long id;
    private String master;

    public GroupTransferDto(Group group) {
        BeanUtils.copyProperties(group, this);
    }
    public Group toEntity(Group group){
        group.setId(this.id);
        group.setImgUrl(group.getImgUrl());
        group.setTitle(group.getTitle());
        group.setCategory(group.getCategory());
        group.setMaster(this.master);
        group.setDescription(group.getDescription());
        group.setMembers(group.getMembers());
        group.setOpenAt(group.getOpenAt());

        return group;
    }
}
