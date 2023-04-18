package com.hariyali.hariyali_project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hariyali.hariyali_project.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report,Integer>{

	
}
