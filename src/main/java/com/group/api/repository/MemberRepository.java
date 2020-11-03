package com.group.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.api.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
