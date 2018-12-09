package com.jaesay;

import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.jaesay.domain.Role;
import com.jaesay.domain.Member;
import com.jaesay.repository.MemberRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class MemberTests {
	
	@Autowired
	MemberRepository repo;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testInsert() {
		
		for(int i=0; i<=10; i++) {
			Member member = new Member();
			member.setMemberName("user" + i);
			member.setPassword(passwordEncoder.encode("1234"));
			member.setEmail("user" + i + "@test.com");
			member.setEnabled(true);
			member.setFirstName("fn" + i);
			member.setLastName("ln" + i);
			
			Role role = new Role();
			if(i<=5) {
				role.setRoleName("USER");
			} else {
				role.setRoleName("ADMIN");
			}
			member.setRoles(Arrays.asList(role));
			
			repo.save(member);
		}
		
	}
	
	@Test
	public void testRead() {
		Optional<Member> result = repo.findById("user85");
		result.ifPresent(user->log.info("user" + user));
	}
}
