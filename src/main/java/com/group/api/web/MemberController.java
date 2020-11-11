package com.group.api.web;

import com.group.api.domain.Group;
import com.group.api.domain.Member;
import com.group.api.domain.service.MemberService;
import com.group.api.web.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.web.bind.annotation.*;

import com.group.api.domain.service.GroupService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import javax.management.ValueExp;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {"2. Members"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/groups/") //컨트롤러 기본 URL
public class MemberController {

    private final MemberService memberService;
    private final GroupService groupService;

    @ApiOperation(value = "Member 리스트 출력", notes = "전체 멤버 리스트를 출력한다.")
    @GetMapping("/member")
    public List<MemberDto> getMembers(){
        List<Member> memberList = memberService.getMembers();
        List<MemberDto> memberDtoList = memberList.stream().map(member -> new MemberDto(member)).collect(Collectors.toList());
        return memberDtoList;
    }

    @ApiOperation(value = "Member 디테일 조회", notes = "memberId 값을 이용하여 조회한다.")
    @GetMapping("{groupId}/member/{memberId}")
    public Member getMember(@PathVariable Long memberId) {return memberService.getMember(memberId);}

    @ApiOperation(value = "Member 추가", notes = "멤버 타입을 활용항 데이터를 받아온다.")
    @PostMapping("{groupId}/member")
    public Member addMember(@RequestBody MemberDto memberDto,@PathVariable Long groupId){

        //member에 넣어줄 그룹의 정보를 가져온다.
        Group group = groupService.getGroup(groupId);
        //DB에 저장할 member 정보를 만들어준다 (DTO에서 Domain으로 변환)
        Member newAddMember = memberDto.toDomain(group);
        //만들어진 member객체를 실제 디비에 저장한다
        return memberService.addMember(newAddMember);
    }
    @ApiOperation(value = "Member 수정", notes = "Member의 내용을 수정한다.")
    @PutMapping("{groupId}/member")
    public Member modifyMember(@RequestBody MemberDto memberDto, @PathVariable Long groupId){
        Group group = groupService.getGroup(groupId);
        Member newModifyMember = memberDto.toDomain(group);
        return memberService.modifyMember(newModifyMember);
}
    @ApiOperation(value = "Member 삭제", notes = "MemberId를 받아와서 Member을 삭제한다.")
    @DeleteMapping("{groupId}/member/{memberId}")
    public String deleteMember(@PathVariable Long groupId, @PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return "제거 완료";
    }
}
