package com.org.creater.OrganizationCreater.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "organization")

public class Organization {
	
	@Id
	@Column(name = "oid")
	@GeneratedValue
	//@OneToMany(mappedBy = "organization", cascade = javax.persistence.CascadeType.REMOVE)
	private Long orgId;
	
	


	@JoinColumn(name="user_id", nullable=false ,referencedColumnName = "id"	)
	@ManyToOne
	private User userId;
	
	private String orgName;
	private Date dateCreated;
	private String description;
	private String email;
	
	
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Organization() {
		
	}

	public Long getOrgId() {
		return orgId;
	}
	
	public User getUser() {
		return userId;
	}


	public void setUser(User user) {
		this.userId = user;
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
