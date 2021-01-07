package com.org.creater.OrganizationCreater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.creater.OrganizationCreater.entity.User;
import com.org.creater.OrganizationCreater.exceptions.UserNotFoundException;
import com.org.creater.OrganizationCreater.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public UserService() {
	}
	
	public User addUser(User user) {
		
		User newUser=new User();
		newUser.setEmail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setPhone(user.getPhone());
		//newUser.setPassword(user.getPassword());
		//newUser.setPassword(encoder.encode(user.getPassword());
		
		return repository.save(user);
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	//handling with custom exception
	public User getUserById(Long id){
		User user= repository.findById(id).orElseThrow(UserNotFoundException::new);
		user.setPassword(null);
		
		return user;
	}
	
	public List<User> getUserByName(String name){
		return repository.findByName(name);
	}

	public long deleteUser(long id) {
		if(repository.findById(id).orElse(null)!=null) {
			repository.deleteById(id);
			return id;
		}
		return -1;
	}

	public User updateUser(User user) {
		return repository.save(user);
	}
}
