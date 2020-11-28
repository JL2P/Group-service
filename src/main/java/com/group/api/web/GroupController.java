package com.group.api.web;

import com.group.api.config.JwtTokenProvider;
import com.group.api.domain.Group;
import com.group.api.domain.service.GroupService;
import com.group.api.domain.service.LikeService;
import com.group.api.web.dto.GroupCreateDto;
import com.group.api.web.dto.GroupDto;
import com.group.api.web.dto.GroupModifyDto;


import com.group.api.web.dto.GroupTransferDto;
import com.group.api.web.dto.groupPoint.GroupRankGroupDto;
import com.group.api.web.dto.groupTodo.GroupTodoDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"1. Group"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class GroupController {

    private final GroupService groupService;
    private final JwtTokenProvider jwtTokenProvider;
    private final LikeService likeService;

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
    public GroupDto getGroup(@PathVariable Long groupId, HttpServletRequest request) {
        //HttpServletRequest request => http요청보낸거에서 헤더를 받아오기 위해서
        // 그룹Todo의 좋아요를 눌렀는지 체크하기위해서는 유저정보가 필요하다.
        // 그래서 토큰에서 유저정보를 취득하여 내가 좋아요를 누를 Todo인지 체크한다.

        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        Group group = groupService.getGroup(groupId);
        GroupDto groupDto = new GroupDto(group);

        //groupTodo에 GroupTodo리스트 데이터 넣어줌
        //Group에 GroupTodo리스트도 있어야 할 것 같아서 넣어줌
        //likeService.checkLiked(groupTodo, accountId) 이부분은 내가 그룹Todo를 좋아요를 눌렀는지 확인하는 부분
        groupDto.setGroupTodos(
                group.getGroupTodos().stream().map(
                        groupTodo -> new GroupTodoDto(groupTodo, likeService.checkLiked(groupTodo, accountId))
                ).collect(Collectors.toList()));
    	return groupDto;
    }
    
    @ApiOperation(value = "Group 추가", notes = "GroupCreateDto 타입을 이용하여 데이터를 받아온다.")
    @PostMapping()
    public GroupDto addGroup(@RequestBody GroupCreateDto groupCreateDto) {
    	Group newAddGroup = groupCreateDto.toDomain();
    	return new GroupDto(groupService.addGroup(newAddGroup));
    }
    
    @ApiOperation(value = "Group 수정", notes = "GroupDetail 변경 설정을 저장한다.")
    @PutMapping()
    public GroupModifyDto modifyGroup(@RequestBody GroupModifyDto groupModifyDto) {
    	Group modifyGroup = groupService.getGroup(groupModifyDto.getId());

    	Group group = groupModifyDto.toEntity(modifyGroup);
    	return new GroupModifyDto(groupService.modifyGroup(group));
    }

    @ApiOperation(value = "Group 수정 그룹장 양도", notes = "GroupDetail 변경 설정을 저장한다.")
    @PutMapping("transfer")
    public GroupTransferDto modifyTransfetGroup(@RequestBody GroupTransferDto groupTransferDto) {
        Group modifyGroup = groupService.getGroup(groupTransferDto.getId());

        Group group = groupTransferDto.toEntity(modifyGroup);
        return new GroupTransferDto(groupService.modifyGroup(group));
    }

    @ApiOperation(value = "Group 삭제", notes = "GroupId를 받아와서 group을 삭제한다.")
    @DeleteMapping("{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
    	groupService.deleteGroup(groupId);
    }


    @ApiOperation(value = "GroupRank Group데이터 매핑", notes = "요청받은 GroupRankGroupDto에서 GroupId를 하나씩 취득하여 Group객첼를 넣어준뒤 리턴한다.")
    @PostMapping("/groupRank/mapping")
    public List<GroupRankGroupDto> groupPointMappingToGroup(@RequestBody ArrayList<GroupRankGroupDto> GroupRankGroupDtos){
        for(int i=0; i< GroupRankGroupDtos.size(); i++) {
            long groupId = GroupRankGroupDtos.get(i).getGroupId();
            GroupRankGroupDtos.get(i).setGroup(groupService.getGroup(groupId));
        }
        return GroupRankGroupDtos;
    }
}
