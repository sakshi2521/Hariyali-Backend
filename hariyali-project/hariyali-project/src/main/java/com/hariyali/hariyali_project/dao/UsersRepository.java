package com.hariyali.hariyali_project.dao;


import org.springframework.data.domain.Pageable;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.entity.Users;

import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	
	
	Users findByUserId(Integer userId);
	
	Users findByUserName(String username);
	
	Users findByUserCode(String usercode);
	
	public Page<Users> findAllByOrderByUserIdDesc(Pageable paging);
	
	Boolean existsByuserName(String username);

	Users findByUserEmail(String email);
	
	Users findByDonorId(String donorId);
	
//	@Query(value = "Select * from users usr where usr.user_id =:user_id", nativeQuery = true)
//	public List<Users> findByUsertypId(Integer user_id);
	
	@Query(value="select um.first_name as firstName,um.last_name as lastName,um.phone as userPhone,um.email as userEmail,"
			+ "um.designation as userDesignation,um.company_name as userCompanyName,um.user_address as userAddress,um.donor_id as donorId,"
			+ "r.role_name as role from hariyalidbletest.user_master um left join hariyalidbletest.roles r on um.role_id = r.role_id"
			+ " where um.user_id=?",nativeQuery = true)
	public Map<String,String> getUserDetails(int userId);
	
	@Query(value="select count(user_id) as no_of_donors from hariyalidbletest.user_master",nativeQuery=true)
	public long getDonorCount();
	
}
