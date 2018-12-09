package com.jaesay.repository;

import org.springframework.data.repository.CrudRepository;

import com.jaesay.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
}
