package com.org.creater.OrganizationCreater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.creater.OrganizationCreater.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public List<User> findByFirstName(String name);
	public User findByEmail(String email);
	
	
}
