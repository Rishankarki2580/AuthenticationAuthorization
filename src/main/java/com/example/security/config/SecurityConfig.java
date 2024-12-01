package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	//User Creation
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		
		//In memory user detail setup for two user
		UserDetails admin = User.withUsername("Rishan").password(encoder.encode("123"))
				.roles("ADMIN", "USER").build();
		
		UserDetails user = User.withUsername("Yam").password(encoder.encode("123"))
				.roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	//Configuration HttpSecurity
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf -> csrf.disable())
		//Disable CSRF for simplicity
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/welcome").permitAll() //Permit all access to /auth/welcome
				.requestMatchers("/auth/user/**").authenticated() //Require authentication for /auth/user/**
				.requestMatchers("/auth/admin/**").authenticated() //Require authentication for /auth/admin/**
				).formLogin(withDefaults()); //Enable form-based login
		
		return http.build();
	}
	
	
	//Password Encoding
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
