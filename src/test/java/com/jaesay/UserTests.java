package com.jaesay;

import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.jaesay.domain.AppRole;
import com.jaesay.domain.AppUser;
import com.jaesay.repository.AppUserRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class UserTests {
	
	@Autowired
	AppUserRepository repo;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testInsert() {
		
		for(int i=0; i<=10; i++) {
			AppUser user = new AppUser();
			user.setUserName("user" + i);
			user.setPassword(passwordEncoder.encode("1234"));
			user.setEmail("user" + i + "@test.com");
			user.setEnabled(true);
			user.setFirstName("fn" + i);
			user.setLastName("ln" + i);
			
			AppRole role = new AppRole();
			if(i<=5) {
				role.setRoleName("USER");
			} else {
				role.setRoleName("ADMIN");
			}
			user.setRoles(Arrays.asList(role));
			
			repo.save(user);
		}
		
	}
	
	@Test
	public void testRead() {
		Optional<AppUser> result = repo.findById("user85");
		result.ifPresent(user->log.info("user" + user));
	}
}
