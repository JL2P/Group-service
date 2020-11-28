package com.group.api.web.dto.groupPoint;

import com.group.api.domain.Group;
import com.group.api.web.dto.GroupDto;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GroupRankGroupDto {
    private Long groupRankId;
    private Long groupId;
    private GroupDto group;
    private int groupTotal;

    public void setGroup(Group group){
        this.group = new GroupDto(group);
    }
}
