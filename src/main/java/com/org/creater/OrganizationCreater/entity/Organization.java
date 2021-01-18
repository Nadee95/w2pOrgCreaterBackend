package com.org.creater.OrganizationCreater.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "organization")

public class Organization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "oid")
	@GeneratedValue
	private Long orgId;

	@JoinColumn(name="user_id", nullable=false ,referencedColumnName = "id"	)
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

	private String orgName;
	private Date dateCreated;
	private String description;
	private String email;
	
	@JsonIgnore()
	@OneToMany(mappedBy = "organization", cascade= CascadeType.ALL,fetch=FetchType.EAGER, orphanRemoval = true)
	private List<Member> members;
	
	//@OneToMany(mappedBy = "oid", cascade= CascadeType.ALL,fetch=FetchType.LAZY, orphanRemoval = true)
	
	public User getUserId() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
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
	
	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
	
	
}
