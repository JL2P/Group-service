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
    public List<GroupDto> getGroups(){
        // 전체 그룹 리스트를 가져온다
        List<Group> groupList = groupService.getGroups();
        //그룹리스트안의 모든 그릅운 DTO로 바꿔준다,
        List<GroupDto> groupDtoList = groupList.stream().map(group->new GroupDto(group)).collect(Collectors.toList());
    	/*js의 map이랑 같음(밑에는 js코드)
    	* grouplist.map(group=>new GroupModel(group))
    	* 
    	* */
        
        return groupDtoList;
    }

    /***
     * 컨트롤러에서 리턴 해줄때 항상 DTO로 바꿔줘야함
     * 그냥 넘겼을경우 연관과계
     * 
     * Group은 실제테이블과 연관된애 ENTITY => JSON으로 바꿀때 연관관계가 있으면 변환하다가 에러남
     * 그래서 Group을 DTO로 바꿔주고, Group안에 있는 member도 Dto로 바꿔줘야함 그래야 JSON으로 변환할수 있음
     * 그래서 DTO사용하는거
     * 이작은은 DTO내에 생성를 생성하는부분, toDomain에서 구현한다,
     */
    @ApiOperation(value = "Group 디테일 정보 조회", notes = "groupId 값을 이용하여 조회한다.")
    @GetMapping("{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        Group group = groupService.getGroup(groupId);
    	return new GroupDto(group);
    }
    
    @ApiOperation(value = "Group 추가", notes = "GroupCreateDto 타입을 이용하여 데이터를 받아온다.")
    @PostMapping()
    public GroupDto addGroup(@RequestBody GroupCreateDto groupCreateDto) {
    	Group newAddGroup = groupCreateDto.toDomain();
    	return new GroupDto(groupService.addGroup(newAddGroup));
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
