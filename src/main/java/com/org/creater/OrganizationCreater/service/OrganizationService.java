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
		System.out.println(organization);
		User user= userService.getUserById(organization.getUser().getId());
		organization.setUser(user);
		return this.repository.save(organization);
	}
	
	
	//update 
	public Organization updateOrganization(Organization organization) {
		Organization org=this.getOrganizationById(organization.getOrgId());
		
		org.setEmail(organization.getEmail());
		org.setOrgName(organization.getOrgName());
		org.setDescription(organization.getDescription());
		return this.repository.save(org);
	}
	//getALLByUid
	public Organization getOrganizationById(long oid) {
		return this.repository.getOne(oid);
	}
	
	//getAll org by user id
	public List<Organization> getOrganizationsByUserId(Long id){
		
		return this.repository.findAllByUser_id(id);
	}
	//delete
	public long deleteOrganizationById(long id) {
		this.repository.deleteById(id);
		return -1; 
	}
	

}
