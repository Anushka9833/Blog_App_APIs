package com.anushka.blogtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.anushka.blogtest.security.CustomUserDetailService;

@Configuration
public class SecurityConfig extends SecurityConfigurerAdapter{
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	     http.csrf().disable().authorizeHttpRequests().
	     anyRequest().authenticated().and().httpBasic();
	     
	     http.authenticationProvider(authenticationProvider());
	     
	     return http.build();
	 }
	
	 @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(customUserDetailService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	 
	 

}
