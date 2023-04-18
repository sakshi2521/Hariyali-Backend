package com.hariyali.hariyali_project.dao;

import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends JpaRepository<Packages, Integer>{

	Packages findByPackageId(Integer packageId);
	
	Packages findByTitle(String packageTtitle);
	
	public Page<Packages> findAllByOrderByPackageIdDesc(Pageable paging);
	
	Boolean existsByTitle(String packageTitle);

}
