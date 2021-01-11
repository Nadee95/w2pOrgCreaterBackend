package com.org.creater.OrganizationCreater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.creater.OrganizationCreater.entity.User;
import com.org.creater.OrganizationCreater.model.AuthenticationRequest;
import com.org.creater.OrganizationCreater.model.AuthenticationResponse;
import com.org.creater.OrganizationCreater.service.UserAuthDetailService;
import com.org.creater.OrganizationCreater.service.UserService;
import com.org.creater.OrganizationCreater.util.JwtUtil;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserAuthDetailService authDetailService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	public UserController() {
		
	}
	
	@GetMapping("/")
	public String home( ) { 
		
		return "welcome ";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		if(this.service.getUserByEmail(authRequest.getEmail())==null) {
			return ResponseEntity.ok(null);
		}
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
					authRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect Email and Passsword",e);
		}
		
		final UserDetails userDetails = authDetailService.loadUserByUsername(authRequest.getEmail());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) { 
		return this.service.addUser(user);
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return this.service.getAllUsers();
	}
	
	@GetMapping("/getUserById/{id}")
	public User getUser(@PathVariable Long id) {
		return this.service.getUserById(id);
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public long deleteUser(@PathVariable long id) {
		return this.service.deleteUser(id);
	}
	
	@PutMapping("/updateUser")
	public User updateUser(@RequestBody User user) {
		return this.service.updateUser(user);
	}
}
