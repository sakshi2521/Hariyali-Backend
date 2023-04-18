package com.hariyali.hariyali_project.dao;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.entity.DonarDetails;
import com.hariyali.hariyali_project.entity.Packages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DonarDetailsRepository  extends JpaRepository<DonarDetails, Integer> {
	
	DonarDetails findByDetailId(Integer detail_Id);
	
	@Query(value= "SELECT D.detailId as detailId, file_content.file_name as file_name,file_content.user_id as user_id, D.DonarName as donarName,D.financial_year as financial_year,D.assesment_year as assesment_year"
			+ " FROM donar_form_details as D "
			+ " INNER JOIN file_content ON D.file_id=file_content.file_id"
			+ " where file_content.user_id=?", nativeQuery = true)
	public List<Map<String,String>> getByDonarDetails(String id);
	
//	DonarDetails findByTitle(String packageTtitle);
	
	public Page<DonarDetails> findAllByOrderByDetailIdDesc(Pageable paging);
	
//	Boolean existsByTitle(String packageTitle);

	

}
