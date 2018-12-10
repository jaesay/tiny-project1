package com.jaesay.Social;

import java.util.Arrays;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import com.jaesay.domain.Member;
import com.jaesay.domain.Role;
import com.jaesay.repository.MemberRepository;

import lombok.extern.java.Log;

@Log
public class ConnectionSignUpImpl implements ConnectionSignUp {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private EntityManager entityManager;
	 
    public ConnectionSignUpImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

	@Override
	public String execute(Connection<?> connection) {
        Member member = this.createMemberUser(connection);
        return member.getMemberName();
	}
	
	private String findAvailableUserName(String memberName_prefix) {
        Member member = memberRepository.findByMemberName(memberName_prefix);
        if (member == null) {
            return memberName_prefix;
        }
        int i = 0;
        while (true) {
            String memberName = memberName_prefix + "_" + i++;
            member = memberRepository.findByMemberName(memberName);
            if (member == null) {
                return memberName;
            }
        }
    }
	
	public Member createMemberUser(Connection<?> connection) {
		  
		ConnectionKey key = connection.getKey();
        
        log.info("key= (" + key.getProviderId() + "," + key.getProviderUserId() + ")");
  
        UserProfile userProfile = connection.fetchUserProfile();
  
        String email = userProfile.getEmail();
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            return member;
        }
        String memberName_prefix = userProfile.getFirstName().trim().toLowerCase()//
                + "_" + userProfile.getLastName().trim().toLowerCase();
  
        String userName = this.findAvailableUserName(memberName_prefix);       
        String randomPassword = UUID.randomUUID().toString().substring(0, 5);
        String encrytedPassword = bCryptPasswordEncoder.encode(randomPassword);

        member = new Member();
        member.setEnabled(true);
        member.setPassword(encrytedPassword);
        member.setMemberName(userName);
        member.setEmail(email);
        member.setFirstName(userProfile.getFirstName());
        member.setLastName(userProfile.getLastName());
        Role role = new Role();
		role.setRoleName("USER");
		member.setRoles(Arrays.asList(role));
  
        this.entityManager.persist(member);

        return member;
    }

}
