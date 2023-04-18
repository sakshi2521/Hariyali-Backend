package com.hariyali.hariyali_project.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hariyali.hariyali_project.entity.FileContent;

@Repository
public interface FileContentRepository extends JpaRepository<FileContent,Long>{

	public FileContent findByfileId(long bulletinId);
	
	
}
