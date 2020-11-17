package com.group.api.web;

import com.group.api.config.JwtTokenProvider;
import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.service.GroupService;
import com.group.api.domain.service.GroupTodoService;
import com.group.api.domain.service.LikeService;
import com.group.api.web.dto.GroupModifyDto;
import com.group.api.web.dto.groupTodo.GroupTodoAddDto;
import com.group.api.web.dto.groupTodo.GroupTodoDto;
import com.group.api.web.dto.groupTodo.GroupTodoModifyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"3. GroupTodo"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups") //컨트롤러 기본 URL
public class GroupTodoController {

    private final GroupTodoService groupTodoService;
    private final GroupService groupService;
    private final JwtTokenProvider jwtTokenProvider;
    private final LikeService likeService;

    @ApiOperation(value = "GroupTodo 리스트 출력", notes = "전체 그룹 투두 리스트를 출력한다.")
    @GetMapping("/allTodos")
    public List<GroupTodoDto> getAllGroupTodos(HttpServletRequest request){
        // 그룹Todo의 좋아요를 눌렀는지 체크하기위해서는 유저정보가 필요하다.
        // 그래서 토큰에서 유저정보를 취득하여 내가 좋아요를 누를 Todo인지 체크한다.
        //토큰 취득
        String token = jwtTokenProvider.resolveToken(request);
        //토큰을 Decode하여 AccountId정보 취득
        String accountId = jwtTokenProvider.getAccountId(token);

        List<GroupTodo> groupTodoList = groupTodoService.getAllTodos();
        List<GroupTodoDto> groupTodoDtoList = groupTodoList.stream().map(groupTodo -> new GroupTodoDto(groupTodo,likeService.checkLiked(groupTodo, accountId))).collect(Collectors.toList());
        return groupTodoDtoList;
    }

    @ApiOperation(value = "해당 그룹의 모든 Todo 조회",notes = "내가 선택한 Group의 GroupId를 이용해서 그룹을 찾고, 찾은 그룹내의 모든 Todo들을 조회한다.")
    @GetMapping("/{groupId}/todos")
    public List<GroupTodoDto> getGroupTodos(HttpServletRequest request, @PathVariable Long groupId){
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);

        Group group = groupService.getGroup(groupId);
        List<GroupTodo> groupTodoList = groupTodoService.getTodos(group);
        return groupTodoList.stream().map(groupTodo -> new GroupTodoDto(groupTodo,likeService.checkLiked(groupTodo, accountId))).collect(Collectors.toList());
    }

    @ApiOperation(value = "그룹Todo 상세정보 조회",notes = "내가 선택한 GroupTodo의 상세 정보를 조회한다.")
    @GetMapping("/{groupId}/todos/{groupTodoId}")
    public GroupTodoDto getGroupTodo(HttpServletRequest request, @PathVariable Long groupId, @PathVariable Long groupTodoId){
        String token = jwtTokenProvider.resolveToken(request);
        String accountId = jwtTokenProvider.getAccountId(token);
        GroupTodo groupTodo = groupTodoService.getTodo(groupTodoId);

        return new GroupTodoDto(groupTodo,likeService.checkLiked(groupTodo,accountId));
    }

    @ApiOperation(value = "groupTodo 추가", notes = "groupTodo를 활용한 데이터를 받아온다.")
    @PostMapping("/{groupId}/todos")
    public String addGroupTodo(@RequestBody GroupTodoAddDto groupTodoAddDto, @PathVariable Long groupId){

        //groupTodo에 넣어줄 그룹의 정보를 가져온다.
        Group group = groupService.getGroup(groupId);
        //DB에 저장할 groupTodo 정보를 만들어준다 (DTO에서 Domain으로 변환)
        GroupTodo newAddGroupTodo = groupTodoAddDto.toDomain(group);
        //만들어진 member객체를 실제 디비에 저장한다
        groupTodoService.addTodo(newAddGroupTodo);
        return "GroupTodo 추가 완료";
    }

    @ApiOperation(value = "groupTodo 수정", notes = "groupTodo를 수정한다.")
    @PutMapping("/{groupId}/todos/{groupTodoId}")
    public String modifyGroupTodo(@RequestBody GroupTodoModifyDto groupTodoModifyDto, @PathVariable Long groupId,@PathVariable Long groupTodoId){
        GroupTodo groupTodo = groupTodoService.getTodo(groupTodoId);
        GroupTodo newAddGroupTodo = groupTodoModifyDto.toDomain(groupTodo);
        groupTodoService.modifyTodo(newAddGroupTodo);
        return "GroupTodp 수정 완료";
    }

    @ApiOperation(value = "GroupTodo 삭제", notes = "GroupTodoId를 받아와서 GroupTodo을 삭제한다.")
    @DeleteMapping("/{groupId}/todos/{groupTodoId}")
    public String deleteGroupTodo(@PathVariable Long groupTodoId, @PathVariable Long groupId){
        groupTodoService.deleteTodo(groupTodoId);
        return "계획 삭제 완료.";
    }

}
