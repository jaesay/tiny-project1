package com.jaesay.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jaesay.domain.AppRole;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private UserDetailsService userDetailsService;
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
            .antMatchers("/js/**", "/css/**", "/h2-console/**");
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
        http
        	.authorizeRequests()
        		.antMatchers("/", "/signup", "/login", "/logout").permitAll()
        		.antMatchers("/user/**").access("hasRole('" + AppRole.ROLE_USER + "')")
        		.antMatchers("/admin/**").access("hasRole('" + AppRole.ROLE_ADMIN + "')")
        		.and()
        	.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/user/details")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
            	.logoutUrl("/logout")
            	.logoutSuccessUrl("/")
            	.invalidateHttpSession(true)
            	.and()
            .exceptionHandling()
            	.accessDeniedPage("/access-denied");
        
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
