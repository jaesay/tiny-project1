package com.jaesay.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDetails extends User {

	private static final long serialVersionUID = -1053409539395205216L;

	private static final String ROLE_PREFIX = "ROLE_";

	private Member member;
	
	public CustomUserDetails(Member member) {
		super(member.getMemberName(), member.getPassword(), getAuthorities(member.getRoles()));
	}

	private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(
				role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		
		return list;
	}

}
