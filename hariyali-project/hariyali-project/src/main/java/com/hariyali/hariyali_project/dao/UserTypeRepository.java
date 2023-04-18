package com.hariyali.hariyali_project.dao;

import com.hariyali.hariyali_project.entity.UserType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType,Long>{

	public UserType findByUsertypeId(int usertypId);
	
	public UserType findByUsertypeName(String usertypeName);
}
