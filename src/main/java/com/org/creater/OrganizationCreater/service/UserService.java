package com.org.creater.OrganizationCreater.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.org.creater.OrganizationCreater.entity.User;
import com.org.creater.OrganizationCreater.exceptions.ExistingUserException;
import com.org.creater.OrganizationCreater.exceptions.UserNotFoundException;
import com.org.creater.OrganizationCreater.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	
	PasswordEncoder encoder;
	
	public UserService() {
		encoder= new BCryptPasswordEncoder();
	}
	
	
	//Add new User/ Register
	public User addUser(User user) {
		
		User existingUser;
		
		if((existingUser=repository.findByEmail(user.getEmail()))!=null) {
			if(user.getEmail().equals(existingUser.getEmail())) {
				throw new ExistingUserException();
			}
		}
		
		
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhone(user.getPhone());
		newUser.setPassword(encoder.encode(user.getPassword()));
		
		return repository.save(newUser);
	}
	
	//Get all users for admin panel
	public List<User> getAllUsers(){
		List<User> users=repository.findAll();
		users.forEach((user)->{user.setPassword(null);});
		return users;
	}
	
	//get user by user id /handling with custom exception
	public User getUserById(Long id){
		User user= repository.findById(id).orElseThrow(UserNotFoundException::new);
		System.out.println("\n getUserById \n");
		return this.removePassword(this.removePassword(user));
	}
	
	//get user by name -> can be many -> return a list
	public List<User> getUserByName(String name){
		List<User> users=repository.findByFirstName(name);
		users.forEach((user)->{user.setPassword(null);});
		return users;
	}

	//delete a user
	public long deleteUser(long id) {
		if(repository.findById(id).orElse(null)!=null) {
			repository.deleteById(id);
			return id;
		}
		return -1;
	}
	
	//update a user
	public User updateUser(User user) {
		
		User updatedUser=repository.save(user);
		
		return this.removePassword(updatedUser);
	}
	
	//get user by email also used for auth-> return one
	public User getUserByEmail(String email) {
		System.out.println("\n getUserByEmail \n");
		return this.repository.findByEmail(email);
	}
	
	//only for authorization
	public String getUserPasswordByEmail(String email) {
		System.out.println("\n getUserPasswordByEmail \n"+email);
		return repository.findByEmail(email).getPassword();
	}
	
	//return user without password
	public User removePassword(User user) {
		System.out.println("before "+ user.getPassword());
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPhone(user.getPhone());
		newUser.setPassword("");
		
		
		System.out.println("after "+ user.getPassword());
		
		return user;
	}
	
	
}
