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
    
    public MemberDto(Member member){
    	BeanUtils.copyProperties(member, this);
    }
    
    public Member toDomain() {
    	Member member = new Member();
    	BeanUtils.copyProperties(this,member);
    	return member;
    }
}
