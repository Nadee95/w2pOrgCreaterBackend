package com.org.creater.OrganizationCreater.entity;

import java.util.Date;
import java.util.List;

public class OrganizationDTO {
	
	private Long orgId;
	public OrganizationDTO(Long orgId, User userId, String orgName, Date dateCreated, String description, String email,
			List<Member> members) {
		super();
		this.orgId = orgId;
		this.userId = userId;
		this.orgName = orgName;
		this.dateCreated = dateCreated;
		this.description = description;
		this.email = email;
		this.members = members;
	}

	private User userId;
	private String orgName;
	private Date dateCreated;
	private String description;
	private String email;
	private List<Member> members;
	
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}	
