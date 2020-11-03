package com.group.api.domain.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.group.api.domain.Member;
import com.group.api.exception.GroupNotExistException;

public interface MemberService {
	
    public List<Member> getMembers() throws NoSuchElementException;
    public Member getMember(Long memberId) throws NoSuchElementException;
    public Member addMember(Member member) throws GroupNotExistException;
    public Member modifyMember(Member member) throws NoSuchElementException;
    public void deleteMember(Long memberId) throws NoSuchElementException;
    public boolean isExist(Long memberId);

}
