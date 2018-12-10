package com.jaesay.repository;

import org.springframework.data.repository.CrudRepository;

import com.jaesay.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
	public Member findByEmail(String email);
	public Member findByMemberName(String memberName);
}
