package com.hariyali.hariyali_project.service;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dto.DonarDetailsDto;
import com.hariyali.hariyali_project.dto.DonarDetailsRequest;
import com.hariyali.hariyali_project.dto.DonarDetailsResponse;
import com.hariyali.hariyali_project.entity.DonarDetails;

import org.springframework.data.jpa.repository.Query;


public interface DonarDetailsService {

	
	public Map<String, Object> getAllDetails(int pageNo, int pageSize);
	
	public void createNew(DonarDetails donarDetails);
	
	
	public List<DonarDetailsDto> getByDonarDetails(String id);
	
//public Packages assignPlantToPackage(int packageId,int plantId);
	
	public DonarDetails getByDetailId(int DetailId);
	
	
	public DonarDetailsResponse<DonarDetails> saveDonarDetails(DonarDetailsRequest donarDetails);
	
	
	
	
}
