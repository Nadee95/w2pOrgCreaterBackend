package com.org.creater.OrganizationCreater.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.creater.OrganizationCreater.entity.Organization;
import com.org.creater.OrganizationCreater.service.OrganizationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	public OrganizationController() {
		
	}
	
	@PostMapping("/organization/addOrganization")
	public Organization addOrganization(@RequestBody Organization organization) {
		
		return this.service.addOrganization(organization);	
	}
	
	@GetMapping("/organization/getAllOrganizations/{id}")
	public List<Organization> getAllRelaventOrganizations(@PathVariable Long id){
		System.out.println(id);
		return this.service.getOrganizationsByUserId(id);
	}
	

}
