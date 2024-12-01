package com.example.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome, this endpoint is not secure";
	}
	
	@GetMapping("/user/userProfile")
	@PreAuthorize("hasRole('USER')") //User has role for role based access control
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasRole('ADMIN')") //Admin has role for role based access control
	public String adminProfile() {
		return "Welcome to Admin Profile";
	}
}
