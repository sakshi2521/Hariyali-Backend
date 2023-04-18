package com.hariyali.hariyali_project.controller;

import java.util.Map;

import com.hariyali.hariyali_project.dao.PackagesRepository;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PackagesRequest;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.exception.CustomException;
import com.hariyali.hariyali_project.service.PackagesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class PackageController {
	private static final Logger logger = LoggerFactory.getLogger(PackageController.class);
	
	@Autowired
	private PackagesService packageService;
	
	@Autowired
	private PackagesRepository packageRepo;
	
	
	
	
	// Get All Packages
		@GetMapping("/getAllPackages")
		public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "1") Integer pageNo,
				@RequestParam(defaultValue = "5000") Integer pageSize) {
			System.out.println("in package controller");
			logger.debug("Getting all Packages");
			Map<String, Object> packages = this.packageService.getAllPackages(pageNo, pageSize);
			if (packages == null) {
				String message = "No Package found";
				String details = "Please check that data exists";
				logger.error(message + "," + details);
				return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(packages, HttpStatus.OK);
		}

		// get package by package Id

		@GetMapping("/package-by-id/{id}")
		public ResponseEntity<Object> getByPackgeId(@PathVariable String id) {
			Packages pkg = this.packageService.getByPackageId(Integer.parseInt(id));
			if (pkg == null) {
				String message = "Package with package name " + id + " not found";
				String details = "Please check user name";
				logger.error(message + "," + details);
				return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(pkg, HttpStatus.OK);
		}

	//get package by package title

		@GetMapping("/package-by-title/{title}")
		public ResponseEntity<Object> getByPackageTitle(@PathVariable String title) {
			Packages packages = this.packageService.getByPackageTitle(title);
			if (packages == null) {
				String message = "package with title " + title + " not found";
				String details = "Please check the package title";
				logger.error(message + "," + details);
				return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(packages, HttpStatus.OK);
		}

		// Add Package
		@PostMapping("/AddPackage")
		public ResponseEntity<Object> addPackage(@RequestBody PackagesRequest packageRequest) {
			PackageResponse<Packages> pkg = null;
			System.err.println("in controller");
			try {
				System.out.println("in try");
				
				pkg = this.packageService.savePackage(packageRequest);
				logger.debug("Saving Package- {}", packageRequest.getTitle());
			} catch (Exception e) {
				System.err.println("in catch");
				return new ResponseEntity<Object>(new CustomException(e.getMessage(), e.getMessage()),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(pkg, HttpStatus.OK);
		}
		
		
//		@PostMapping("/AddPackage")
//		public PackageResponse create(@RequestBody PackagesRequest packages)
//		{
//			return packageRepo.save(packages);
//		}
//		
		
//	    public ResponseEntity<Object> createPackage(@RequestBody Packages articleEntity)
//	    {
//	        packageService.createNew(articleEntity);
//	        return new ResponseEntity<>("New package Created", HttpStatus.CREATED);
//	    }
		
//		@PutMapping("/package/{packageId}/{plantId}")
//		public Packages updatePlantPackage(@PathVariable int packageId,@PathVariable int plantId) {
//			
//			return packageService.assignPlantToPackage(packageId, plantId);
//		}


		// update user
		@PutMapping("/updatPackage/{packageTitle}")
		public ResponseEntity<?> updateUser(@PathVariable(value = "title") String title,
				@RequestBody PackagesRequest pkg, HttpServletRequest request) {
			PackageResponse<Packages> response = new PackageResponse<Packages>();
			try {
				response = this.packageService.updatePackage(title, pkg, request);
				return new ResponseEntity<>(response, response.getStatusCode());
			} catch (Exception e) {
				logger.error("error occured" + e);
			}
			response.setMessage("Something Went wrong");
			response.setStatus("ERROR");
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(response, response.getStatusCode());
		}

		// delete user
		@DeleteMapping("/deletePackage/{title}")
		public ResponseEntity<?> deleteUser(@PathVariable(value = "title") String title) {
			PackageResponse<Packages> response = new PackageResponse<Packages>();
			try {
				response = this.packageService.deletePackage(title);
				return new ResponseEntity<>(response, response.getStatusCode());
			} catch (Exception e) {
				logger.error("error occured" + e);
			}
			response.setMessage("Something Went wrong");
			response.setStatus("ERROR");
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(response, response.getStatusCode());
		}

}
