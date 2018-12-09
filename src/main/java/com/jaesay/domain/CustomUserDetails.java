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

	private AppUser user;
	
	public CustomUserDetails(AppUser user) {
		super(user.getUserName(), user.getPassword(), getAuthorities(user.getRoles()));
	}

	private static List<GrantedAuthority> getAuthorities(List<AppRole> roles) {
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(
				role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		
		return list;
	}

}
