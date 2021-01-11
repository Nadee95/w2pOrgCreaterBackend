package com.org.creater.OrganizationCreater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.creater.OrganizationCreater.entity.Organization;
import com.org.creater.OrganizationCreater.entity.User;
import com.org.creater.OrganizationCreater.repository.OrganizationRepository;

@Service
public class OrganizationService {
	
	@Autowired
	private OrganizationRepository repository;
	
	@Autowired
	private UserService userService;
	
	public OrganizationService() {
		
	}
	
	//addORG
	public Organization addOrganization(Organization organization) {
		User user= userService.getUserById(organization.getUser().getId());
		organization.setUser(user);
		return this.repository.save(organization);
	}
	
	//delete
	//update
	//getALLByUid
	public Organization getOrganizationById(long oid) {
		return this.repository.getOne(oid);
	}
	
	public List<Organization> getOrganizationsByUserId(Long id){
		return this.repository.findAllByUserId_id(id);
	}
	

}
