package com.org.creater.OrganizationCreater.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.creater.OrganizationCreater.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long>{

	List<Organization> findAllByUserId_id(Long id);

	//List<Organization> findByUser_id_id(Long id);

}
