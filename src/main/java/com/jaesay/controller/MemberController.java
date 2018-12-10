package com.jaesay.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.jaesay.domain.Member;
import com.jaesay.domain.Role;
import com.jaesay.form.SignupForm;
import com.jaesay.service.MemberService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
  
    @Autowired
    private UsersConnectionRepository connectionRepository;
	
	@GetMapping("signup")
	public void signup(WebRequest request, Model model) {
		//model.addAttribute("signupForm", new SignupForm());
		
		ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);
		
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		SignupForm signupForm = null;
		if (connection != null) {
			signupForm = new SignupForm(connection);
		} else {
			signupForm = new SignupForm();
		}
		model.addAttribute("signupForm", signupForm);
	}
	
	@PostMapping("signup")
	public String signupPOST(@Valid SignupForm signupForm , BindingResult result) {
		
		log.info("SIGNUPFORM: " + signupForm);
		
		if(result.hasErrors()) {
			return "/member/signup";
		}
		
		ModelMapper modelMapper = new ModelMapper();
		Member member = modelMapper.map(signupForm, Member.class);
		
		Role role = new Role();
		role.setRoleName("USER");
		member.setRoles(Arrays.asList(role));

		log.info("MEMBER: " + member);

		memberService.save(member);
		return "redirect:/welcome";
	}
	
	@GetMapping("/welcome")
	public void welcome() {
		
	}
	
	@GetMapping("/details")
	public String viewUserDestils() {
		return "member/details";
	}
	
}
