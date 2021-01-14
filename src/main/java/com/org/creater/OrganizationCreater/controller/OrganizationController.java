package com.org.creater.OrganizationCreater.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.creater.OrganizationCreater.entity.Organization;
import com.org.creater.OrganizationCreater.entity.OrganizationDTO;
import com.org.creater.OrganizationCreater.service.MemberService;
import com.org.creater.OrganizationCreater.service.OrganizationService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrganizationController {
	
	@Autowired
	private OrganizationService service;
	
	@Autowired
	private MemberService memberservice;
	
	public OrganizationController() {
		
	}
	
	@PostMapping("/organization/addOrganization")
	public Organization addOrganization(@RequestBody Organization organization) {
		
		return this.service.addOrganization(organization);	
	}
	
	@GetMapping("/organization/getAllOrganizations/{id}")
	public List<OrganizationDTO> getAllRelaventOrganizations(@PathVariable Long id){
		
		System.out.println(id);
		
		List<Organization> organizations= this.service.getOrganizationsByUserId(id);
		
		List<OrganizationDTO> orgListWithMembers=new ArrayList<>();
			
			
		organizations.forEach(org ->{
			orgListWithMembers.add(new OrganizationDTO(org.getOrgId(),
														org.getUserId(),
														org.getOrgName(),
														org.getDateCreated(),
														org.getDescription(),
														org.getEmail(),
					this.memberservice.getAllMembersByOid(org.getOrgId())
					));
		});
		
		return orgListWithMembers;
		
	}
	
	@GetMapping("/organization/getOrganizationById/{oid}")
	public Organization getOrganizationById(@PathVariable Long oid){
		System.out.println(oid);
		return this.service.getOrganizationById(oid);
	}
	
	@DeleteMapping("/organization/deleteOrganization/{oid}")
	public long deleteOrganizationById(@PathVariable long oid) {
		//Organization org=this.service.getOrganizationById(oid);
		//if(this.memberservice.deleteAllMembersByOid(org)) {
			
		//}
		return this.service.deleteOrganizationById(oid);
		
	}
	
	@PutMapping("/organization/updateOrganization")
	public Organization updateOrganization(@RequestBody Organization organization) {
		
		return this.service.updateOrganization(organization);	
	}

}
