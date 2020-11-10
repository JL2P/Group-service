package com.group.api.domain.logic;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.group.api.domain.Member;
import com.group.api.domain.service.MemberService;
import com.group.api.exception.GroupNotExistException;
import com.group.api.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository;
	
	 @Override
	    public List<Member> getMembers() throws NoSuchElementException {
	        List<Member> members = repository.findAll();
	        return members;
	    }

	    @Override
	    public Member getMember(Long memberId) throws NoSuchElementException {
	        //데이터가 하나도 없을 경우 빈 member객체 반환
	        if(!isExist(memberId)) return new Member();

	        return repository.findById(memberId).orElseThrow(()-> new NoSuchElementException());
	    }

	    @Override
	    public Member addMember(Member member) throws GroupNotExistException {
//	        if(isExist(group.getId()))throw new GroupNotExistException(group.getId().toString());

	        return repository.save(member);
	    }

	    @Override
	    public Member modifyMember(Member member) throws NoSuchElementException {
	        //DB에 member가 존재하는지 확인
//	        if(!isExist(group.getId()))throw new NoSuchElementException(group.getId().toString());
	        return repository.save(member);
	    }

	    @Override
	    public void deleteMember(Long memberId) throws NoSuchElementException {
	        if(!isExist(memberId))throw new NoSuchElementException(memberId.toString());

	        repository.deleteById(memberId);
	    }

	    @Override
	    public boolean isExist(Long memberId) {
	        Optional<Member> memberOpt = repository.findById(memberId);
	        //Optional안에 member객체가 존재하는 경우
	        if(memberOpt.isPresent()) return true;

	        //Optional안에 member객체가 존재하지 않는 경우
	        return false;
	    }
}
