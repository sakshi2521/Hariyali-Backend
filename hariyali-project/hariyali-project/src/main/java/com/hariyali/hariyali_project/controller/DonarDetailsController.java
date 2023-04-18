package com.hariyali.hariyali_project.controller;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dao.DonarDetailsRepository;
import com.hariyali.hariyali_project.dao.PackagesRepository;
import com.hariyali.hariyali_project.dto.DonarDetailsDto;
import com.hariyali.hariyali_project.dto.DonarDetailsRequest;
import com.hariyali.hariyali_project.dto.DonarDetailsResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PackagesRequest;
import com.hariyali.hariyali_project.entity.DonarDetails;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.exception.CustomException;
import com.hariyali.hariyali_project.service.DonarDetailsService;
import com.hariyali.hariyali_project.service.PackagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DonarDetailsController {
	
	@Autowired
	private DonarDetailsService donarService;
	
	@Autowired
	private DonarDetailsRepository donarRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(DonarDetailsController.class);
	
	// Get All Packages
			@GetMapping("/getAllDetails")
			public ResponseEntity<Object> getAllDonarDetails(@RequestParam(defaultValue = "1") Integer pageNo,
					@RequestParam(defaultValue = "5000") Integer pageSize) {
				System.out.println("in package controller");
				logger.debug("Getting all Packages");
				Map<String, Object> donardetails = this.donarService.getAllDetails(pageNo, pageSize);
				
				if (donardetails == null) {
					String message = "No Package found";
					String details = "Please check that data exists";
					logger.error(message + "," + details);
					return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
				}
				
				return new ResponseEntity<>(donardetails, HttpStatus.OK);
			}


			@PostMapping("/AddDetails")
			public ResponseEntity<Object> addDonarDetails(@RequestBody DonarDetailsRequest donarDetailRequest) {
				DonarDetailsResponse<DonarDetails> donarDetails = null;
				try {
					donarDetails = this.donarService.saveDonarDetails(donarDetailRequest);
					logger.debug("Saving Package- {}", donarDetailRequest.getDonarName());
				} catch (Exception e) {
					return new ResponseEntity<Object>(new CustomException(e.getMessage(), e.getMessage()),
							HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(donarDetails, HttpStatus.OK);
			}
			
			@GetMapping("/donar-by-id/{id}")
			public ResponseEntity<Object> getByDonarDetailsId(@PathVariable String id) {
				DonarDetails donarDetails = this.donarService.getByDetailId(Integer.parseInt(id));
				if (donarDetails == null) {
					String message = "Donar Details with Donar Id " + id + " not found";
					String details = "Please check user name";
					logger.error(message + "," + details);
					return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(donarDetails, HttpStatus.OK);
			}
			
			

			@GetMapping("/donar/{id}")
			public List<DonarDetailsDto> getByDonarDetails(@PathVariable String id) {
				
				return this.donarService.getByDonarDetails(id);
			
			}
			
			
}



