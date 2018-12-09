package com.jaesay.support.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import lombok.extern.java.Log;

@Log
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			log.warning(
					"User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
		}
		
		if(request.getRequestURI().equals("/member/signup") || request.getRequestURI().equals("/login")) {
			if (auth != null) {
				response.sendRedirect(request.getContextPath() + "/logout");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/access-denied");			
		}
	}

}
