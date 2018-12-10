package com.jaesay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaesay.Social.SocialUserDetailsImpl;
import com.jaesay.domain.Member;
import com.jaesay.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberName(username);
		
		if (member == null) {
            throw new UsernameNotFoundException("회원(" + username + ")이 존재하지 않습니다.");
        }
		
		return new SocialUserDetailsImpl(member);
		//return new CustomUserDetails(member);
		
		/*return memberRepository.findById(username)
				.filter(m -> m != null)
				.map(m -> new CustomUserDetails(m)).get();*/
	}

}
