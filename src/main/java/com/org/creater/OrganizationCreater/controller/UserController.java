package com.org.creater.OrganizationCreater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.creater.OrganizationCreater.entity.User;
import com.org.creater.OrganizationCreater.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;;
	
	public UserController() {
		
	}
	
	@PostMapping("/addUser")
	public User addUser(@RequestBody User user) { 
		System.out.println("User "+ user.getEmail());
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
