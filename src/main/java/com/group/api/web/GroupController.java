package com.group.api.web;

import com.group.api.domain.Group;
import com.group.api.domain.service.GroupService;
import com.group.api.web.dto.GroupCreateDto;
import com.group.api.web.dto.GroupDto;
import com.group.api.web.dto.GroupModifyDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"1. Group"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class GroupController {

    private final GroupService groupService;
    
    @ApiOperation(value = "Group 리스트 출력", notes = "전체 그룹 리스트를 출력한다.")
    @GetMapping()
    public List<Group> getGroups(){
    	return groupService.getGroups();
    }
    
    @ApiOperation(value = "Group 디테일 정보 조회", notes = "groupId 값을 이용하여 조회한다.")
    @GetMapping("{groupId}")
    public Group getGroup(@PathVariable Long groupId) {
    	return groupService.getGroup(groupId);
    }
    
    @ApiOperation(value = "Group 추가", notes = "GroupCreateDto 타입을 이용하여 데이터를 받아온다.")
    @PostMapping()
    public Group addGroup(@RequestBody GroupCreateDto groupCreateDto) {
    	Group newAddGroup = groupCreateDto.toDomain();
    	return groupService.addGroup(newAddGroup);
    }
    
    @ApiOperation(value = "Group 수정", notes = "GroupDetail 변경 설정을 저장한다.")
    @PutMapping()
    public Group modifyGroup(@RequestBody GroupModifyDto groupModifyDto) {
    	Group group = groupModifyDto.toDomain(); 
    	return groupService.modifyGroup(group);
    }
    
    @ApiOperation(value = "Group 삭제", notes = "GroupId를 받아와서 group을 삭제한다.")
    @DeleteMapping("{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
    	groupService.deleteGroup(groupId);
    }
}
