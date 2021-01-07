package com.org.creater.OrganizationCreater.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.creater.OrganizationCreater.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

}
