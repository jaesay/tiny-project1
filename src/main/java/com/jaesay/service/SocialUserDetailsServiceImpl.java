package com.jaesay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.jaesay.Social.SocialUserDetailsImpl;

public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {

	@Autowired
    private UserDetailsService userDetailService;
	
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		UserDetails userDetails = ((UserDetailsServiceImpl) userDetailService).loadUserByUsername(userId);
		 
        return (SocialUserDetailsImpl) userDetails;
	}

}
