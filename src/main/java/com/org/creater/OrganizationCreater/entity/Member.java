package com.org.creater.OrganizationCreater.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "member")
public class Member {
	
	@Id
	@GeneratedValue
	private Long mid;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="oid", nullable=false ,referencedColumnName = "oid")
	private Organization org;
	private String email;
	
	public Member() {
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}