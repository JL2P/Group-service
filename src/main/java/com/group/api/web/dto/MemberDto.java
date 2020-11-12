package com.group.api.web.dto;

import org.springframework.beans.BeanUtils;

import com.group.api.domain.Group;
import com.group.api.domain.Member;

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
public class MemberDto {
	
	private Long id;
	private String accountId;   // 멤버 ID
    private String confirm;     // (승인 Y / 미승인 N) 승인여부
    private Long groupId;
    private String manager; //관리자
    private String date;
    
    public MemberDto(Member member){
        BeanUtils.copyProperties(member, this);
        this.groupId=member.getGroup().getId();

    }
    
    public Member toDomain(Group group) {
    	Member member = new Member();
    	BeanUtils.copyProperties(this,member);
    	//member를 만들려고하니, member 도메인에 Group이 연관관계로 있어서 반드시 넣어줘야함
        //그래서 controller에서 받아온 groupId를 이용해서 GroupService를 통해 Group객체를 받아서 넣어주는 부분
    	member.setGroup(group);
    	return member;
    }
}
