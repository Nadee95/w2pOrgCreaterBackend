package com.org.creater.OrganizationCreater.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.creater.OrganizationCreater.entity.Member;
import com.org.creater.OrganizationCreater.entity.MemberStatus;
import com.org.creater.OrganizationCreater.entity.Organization;
import com.org.creater.OrganizationCreater.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private OrganizationService organizationService;
	
	public MemberService() {
		// TODO Auto-generated constructor stub
	}
	
	//add member
	public Member addMember(Member member) {
		
		member.setStatus(MemberStatus.PENDING);
		
		return repository.save(member);
	
	}
	//add delete getAllby Oid , 
	public long deleteMember(long id) {
		if(repository.findById(id).orElse(null)!=null) {
			repository.deleteById(id);
			return id;
		}
		return -1;
	}
	//update member status
	public Member updateStatus(MemberStatus status, Long id) {
		Member member=repository.getOne(id);
		member.setStatus(status);
		return repository.save(member);	
	}
	
	//getAll members  to a admin panel
	public List<Member> getAllmembers(){
		List<Member> members=repository.findAll();
		return members;
	}
	
	//getAll members relative to organization
	public List<Member> getAllMembersByOid(Long oid){
		Organization org= organizationService.getOrganizationById(oid);
		return repository.findAllByOrg_OrgId(org.getOrgId());
	}

	public boolean deleteAllMembersByOid(Organization org) {
		// TODO Auto-generated method stub
		return this.repository.deleteAllMembersByOrg(org);
	}

	
}
