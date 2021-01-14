package com.org.creater.OrganizationCreater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.org.creater.OrganizationCreater.entity.Member;
import com.org.creater.OrganizationCreater.entity.Organization;


public interface MemberRepository extends JpaRepository<Member, Long>{
	
	//@Query("SELECT new com.org.OrganizationCreater.entity.Member FROM Member m INNER JOIN  Organization o ON m.org=o.orgId")
	List<Member> findAllByOrg_OrgId(Long oid);



	boolean deleteAllMembersByOrg(Organization org);
	
	
}
