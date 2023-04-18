package com.hariyali.hariyali_project.controller;

import java.util.Map;

import com.hariyali.hariyali_project.dto.PlantRequest;
import com.hariyali.hariyali_project.dto.PlantResponse;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.exception.CustomException;
import com.hariyali.hariyali_project.service.PackagesService;
import com.hariyali.hariyali_project.service.PlantsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PlantController {
	
private static final Logger logger = LoggerFactory.getLogger(PackageController.class);
	
	@Autowired
	private PlantsService plantService;
	
	@Autowired
	private PackagesService packageService;
	
	
	// Get All Packages
			@GetMapping("/getAllPlants")
			public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "1") Integer pageNo,
					@RequestParam(defaultValue = "5000") Integer pageSize) {
				System.out.println("in package controller");
				logger.debug("Getting all Packages");
				Map<String, Object> users = this.packageService.getAllPackages(pageNo, pageSize);
				if (users == null) {
					String message = "No Package found";
					String details = "Please check that data exists";
					logger.error(message + "," + details);
					return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(users, HttpStatus.OK);
			}

	
	
	
	
	
	
	
	// Add Plant
	@PostMapping("/AddPlant")
	public ResponseEntity<Object> addPlant(@RequestBody PlantRequest plantRequest) {
		PlantResponse pkg = null;
		try {
//			packageRequest.setCreated_At(new Date());
			pkg = this.plantService.savePlant(plantRequest);
			logger.debug("Saving Plants- {}", plantRequest.getTitle());
		} catch (Exception e) {
			return new ResponseEntity<Object>(new CustomException(e.getMessage(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pkg, HttpStatus.OK);
		
	}
	
	//Add Plantion plant
	
	@PutMapping("/package/{packageId}/plant/{plantId}")
	public Packages plantationpackages(@PathVariable int packageId,@PathVariable int plantId)
	{
		
		return packageService.CreateManyTable(packageId,plantId);
		
	}
	

}
