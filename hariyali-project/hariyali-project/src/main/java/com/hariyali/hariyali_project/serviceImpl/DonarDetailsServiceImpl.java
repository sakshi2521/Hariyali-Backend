package com.hariyali.hariyali_project.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hariyali.hariyali_project.dao.DonarDetailsRepository;
import com.hariyali.hariyali_project.dto.DonarDetailsDto;
import com.hariyali.hariyali_project.dto.DonarDetailsRequest;
import com.hariyali.hariyali_project.dto.DonarDetailsResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.entity.DonarDetails;
import com.hariyali.hariyali_project.entity.FileContent;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.UserType;
import com.hariyali.hariyali_project.service.DonarDetailsService;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@Service
public class DonarDetailsServiceImpl implements DonarDetailsService {
	
	@Autowired
	private DonarDetailsRepository donarRepo;
	
	@Autowired
	private EntityManager entityManager;


	@Override
	public Map<String, Object> getAllDetails(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo - 1, pageSize);
		Page<DonarDetails> donarDetails = this.donarRepo.findAllByOrderByDetailIdDesc(paging);
		System.out.println("in Donar details service");
		Map<String, Object> response = new HashMap<>();
		response.put("packages", donarDetails.getContent());
		response.put("currentPage", donarDetails.getNumber());
		response.put("totalItems", donarDetails.getTotalElements());
		response.put("totalPages", donarDetails.getTotalPages());
//				session.disableFilter("deletedUserFilter");
		if (donarDetails.hasContent()) {
			return response;
		} else {
			return null;
		}
	}

	@Override
	public void createNew(DonarDetails donarDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DonarDetails getByDetailId(int DetailId) {
		DonarDetails donarDetails = new DonarDetails();
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedUserFilter");
		filter.setParameter("isDeleted", false);
		System.out.println("in package service");
		donarDetails = this.donarRepo.findByDetailId(DetailId);

		session.disableFilter("deletedUserFilter");
		return donarDetails;

	}

	@Override
	public DonarDetailsResponse<DonarDetails> saveDonarDetails(DonarDetailsRequest donarDetails) {
		ObjectMapper mapper = new ObjectMapper();
		DonarDetailsResponse<DonarDetails>  response = new DonarDetailsResponse<DonarDetails>();

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		DonarDetails savedDonarDetails = mapper.convertValue(donarDetails, DonarDetails.class);	
		
		FileContent file = new FileContent();
		file.setFileId(donarDetails.getFileId());
		
		savedDonarDetails.setFile(file);
		
		savedDonarDetails.setDonarName(donarDetails.getDonarName());
//		savedDonarDetails.setFinancial_year(donarDetails.getFinancial_year());
//		savedDonarDetails.setAssesment_year(donarDetails.getAssesment_year());
//		
		savedDonarDetails.setAssesment_year(new Date());
		savedDonarDetails.setFinancial_year(new Date());
		savedDonarDetails = this.donarRepo.save(savedDonarDetails);
		
		response.setMessage("Donar Details Added Successfully");
		response.setStatus("Success");
		response.setStatusCode(HttpStatus.CREATED);
		response.setResult(savedDonarDetails);
		//logger.info("addUser method executed successfully");
		
		return response;
	}

	@Override
	public List<DonarDetailsDto> getByDonarDetails(String id) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<DonarDetailsDto> donarDetailsDto = objectMapper.convertValue(this.donarRepo.getByDonarDetails(id),new TypeReference<List<DonarDetailsDto>>() {});
		 
		return donarDetailsDto;
		
	}

}
