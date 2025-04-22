package com.security;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Data returned from methods is directly written to HTTP response body
@RequestMapping("/auth")
public class UserController {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome, this endpoint is not secure";
	}
	
	@GetMapping("/user/userProfile")
	@PreAuthorize("hasRole('USER')")
	public String userProfile() {
		return "Welcome to user profile";
	}
	
	@GetMapping("/admin/adminProfile")
    @PreAuthorize("hasRole('ADMIN')")  // Use hasRole for role-based access control
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

	
}
