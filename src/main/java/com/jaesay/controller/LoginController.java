package com.jaesay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/access-denied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
}
