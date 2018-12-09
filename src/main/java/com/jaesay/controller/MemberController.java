package com.jaesay.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jaesay.domain.Member;
import com.jaesay.domain.Role;
import com.jaesay.service.MemberService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("signup")
	public void signup() {

	}
	
	@PostMapping("signup")
	public String signupPOST(@ModelAttribute("member") Member member) {
		
		Role role = new Role();
		role.setRoleName("USER");
		member.setRoles(Arrays.asList(role));

		log.info("MEMBER: " + member);
		
		memberService.save(member);
		return "redirect:/member/welcome";
	}
	
	@GetMapping("/welcome")
	public void welcome() {
		
	}
	
	@GetMapping("/details")
	public String viewUserDestils() {
		return "member/details";
	}
	
}
