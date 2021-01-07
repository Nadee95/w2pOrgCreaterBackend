package com.org.creater.OrganizationCreater.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "organization")
public class Organization {
	
	@Id
	@Column(name = "oid")
	@GeneratedValue
	private Long orgId;
	
	@ManyToOne
	@JoinColumn(name="userId", nullable=false ,referencedColumnName = "id")
	private User user;
	private String orgName;
	private Date dateCreated;
	private String description;
	
	
	public Organization() {
		
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
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
	
	
}
