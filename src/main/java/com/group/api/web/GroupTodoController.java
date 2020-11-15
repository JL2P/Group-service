package com.group.api.web;

import com.group.api.domain.Group;
import com.group.api.domain.GroupTodo;
import com.group.api.domain.Member;
import com.group.api.domain.service.GroupService;
import com.group.api.domain.service.GroupTodoService;
import com.group.api.web.dto.GroupTodoDto;
import com.group.api.web.dto.MemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"3. GroupTodo"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class GroupTodoController {

    private final GroupTodoService groupTodoService;
    private final GroupService groupService;

    @ApiOperation(value = "GroupTodo 리스트 출력", notes = "전체 그룹 투두 리스트를 출력한다.")
    @GetMapping("groupTodo")
    public List<GroupTodoDto> getGroupTodos(){
        List<GroupTodo> groupTodoList = groupTodoService.getTodos();
        List<GroupTodoDto> groupTodoDtoList = groupTodoList.stream().map(groupTodo -> new GroupTodoDto(groupTodo)).collect(Collectors.toList());
        return groupTodoDtoList;
    }

    @ApiOperation(value = "groupTodo 추가", notes = "groupTodo를 활용한 데이터를 받아온다.")
    @PostMapping("{groupId}/groupTodo")
    public GroupTodo addGroupTodo(@RequestBody GroupTodoDto groupTodoDto, @PathVariable Long groupId){

        //groupTodo에 넣어줄 그룹의 정보를 가져온다.
        Group group = groupService.getGroup(groupId);
        //DB에 저장할 groupTodo 정보를 만들어준다 (DTO에서 Domain으로 변환)
        GroupTodo newAddGroupTodo = groupTodoDto.toDomain(group);
        //만들어진 member객체를 실제 디비에 저장한다
        return groupTodoService.addTodo(newAddGroupTodo);
    }
    @ApiOperation(value = "GroupTodo 삭제", notes = "GroupTodoId를 받아와서 GroupTodo을 삭제한다.")
    @DeleteMapping("{groupId}/groupTodo/{groupTodoId}")
    public String deleteGroupTodo(@PathVariable Long groupTodoId, @PathVariable Long groupId){
        groupTodoService.deleteTodo(groupTodoId);
        return "계획 삭제 완료.";
    }

}
