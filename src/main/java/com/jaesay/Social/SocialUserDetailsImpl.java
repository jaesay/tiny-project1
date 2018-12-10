package com.jaesay.Social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import com.jaesay.domain.Member;
import com.jaesay.domain.Role;


public class SocialUserDetailsImpl implements SocialUserDetails {

	private static final long serialVersionUID = 7094240390558436639L;
	
	private static final String ROLE_PREFIX = "ROLE_";

	private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
	private Member member;
 
    public SocialUserDetailsImpl(Member member) {
        this.member = member;
 
        for (Role role : member.getRoles()) {
 
            GrantedAuthority grant = new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName());
            this.authorities.add(grant);
        }
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getMemberName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUserId() {
		return member.getMemberId()+"";
	}

}
