package com.hariyali.hariyali_project.serviceImpl;

import java.security.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hariyali.hariyali_project.dao.PackagesRepository;
import com.hariyali.hariyali_project.dao.PlantRepository;
import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PackagesRequest;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Plants;
import com.hariyali.hariyali_project.entity.Users;
import com.hariyali.hariyali_project.service.PackagesService;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.Filter;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PackagesServiceImpl implements PackagesService {

	@Autowired
	private PackagesRepository packageRepo;

	@Autowired
	private PlantRepository plantRepo;

	@Autowired
	private EntityManager entityManager;

	// Get All Packages
	@Override
	public Map<String, Object> getAllPackages(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo - 1, pageSize);
		Page<Packages> packages = this.packageRepo.findAllByOrderByPackageIdDesc(paging);
		System.out.println("in package service");
		Map<String, Object> response = new HashMap<>();
		response.put("packages", packages.getContent());
		response.put("currentPage", packages.getNumber());
		response.put("totalItems", packages.getTotalElements());
		response.put("totalPages", packages.getTotalPages());
//				session.disableFilter("deletedUserFilter");
		if (packages.hasContent()) {
			return response;
		} else {
			return null;
		}
	}

	// Get Package By Id
	@Override
	public Packages getByPackageId(int packageId) {
		Packages packages = new Packages();
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedUserFilter");
		filter.setParameter("isDeleted", false);
		System.out.println("in package service");
		packages = this.packageRepo.findByPackageId(packageId);

		session.disableFilter("deletedUserFilter");
		return packages;

	}

	// Get Package By Title
	@Override
	public Packages getByPackageTitle(String packageTitle) {
		Packages packages = new Packages();
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedUserFilter");
		filter.setParameter("isDeleted", false);

		packages = this.packageRepo.findByTitle(packageTitle);

		session.disableFilter("deletedUserFilter");
		return packages;
	}

	// Add Package
	@Override
	public PackageResponse<Packages> savePackage(PackagesRequest pkg) {
		ObjectMapper mapper = new ObjectMapper();
		PackageResponse<Packages>  response = new PackageResponse<Packages>();

//		if (this.packageRepo.existsByTitle(pkg.getTitle())) {
//			throw new ResponseStatusException(HttpStatus.CONFLICT, "Package is already exists!");
//		}

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Packages savedPackage = mapper.convertValue(pkg, Packages.class);
		savedPackage.setStatus("Created");
		savedPackage.setCreated_At(new Date());
		savedPackage.setIsdeleted(false);

		savedPackage = this.packageRepo.save(savedPackage);
		
		response.setMessage("Package Added Successfully");
		response.setStatus("Success");
		response.setStatusCode(HttpStatus.CREATED);
		response.setResult(savedPackage);
		//logger.info("addUser method executed successfully");
		
		return response;
	}

	
	
//	
//	 public void createArticle(ArticleEntity articleEntity)
//	    {
//	        articleEntity.setTimestamp(new Timestamp(new Date().getTime()));
//	        articleRepository.save(articleEntity);
//	    }
	// Update Package
	@Override
	public PackageResponse<Packages> updatePackage(String title, PackagesRequest pkg, HttpServletRequest request) {
		PackageResponse<Packages> response = new PackageResponse<Packages>();

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Packages packageResponse = this.packageRepo.findByTitle(title);
		if (packageResponse != null) {
			packageResponse.setDescription(pkg.getDescription());
			
			packageResponse.setPrice(pkg.getPrice());
			packageResponse.setUpdated_At(new Date());
			packageResponse.setUpdated_By(new Date());
			packageResponse.setStatus("Created");

			this.packageRepo.save(packageResponse);
			response.setMessage("User Has Been Updated Successfully");
			response.setResult(packageResponse);
			response.setStatus("SUCCESS");
			response.setStatusCode(HttpStatus.OK);
			return response;
		} else {
			response.setMessage("Package Not Found With packge title " + title);
			response.setStatus("ERROR");
			response.setStatusCode(HttpStatus.NOT_FOUND);
			return response;
		}
	}

	// Delete Package
	@Override
	public PackageResponse<Packages> deletePackage(String title) {
		PackageResponse<Packages> response = new PackageResponse<Packages>();
		Packages packageResponse = this.packageRepo.findByTitle(title);
		if (packageResponse != null) {
			packageResponse.setIsdeleted(null);
			this.packageRepo.save(packageResponse);
			response.setMessage("Package Has Been Deleted Successfully");
			response.setResult(packageResponse);
			response.setStatus("SUCCESS");
			response.setStatusCode(HttpStatus.OK);
			return response;
		}
		response.setMessage("Package Not Found With user code" + title);
		response.setStatus("ERROR");
		response.setStatusCode(HttpStatus.NOT_FOUND);
		return response;
	}

	@Override
	public Packages CreateManyTable(int packageId, int plantId) {
		// TODO Auto-generated method stub
//		List<Plants>plantSet=null;
//		
//		Packages packages=packageRepo.findByPackageId(packageId);
//		
//		Plants plants=plantRepo.findByPlantId(plantId);
//		
//		plantSet=packages.getPlants();
//		plantSet.add(plants);
//		packages.setPlants(plantSet);
//		
		 // Create an employee
//		Packages packages = new Packages();
//		packages.setPackageId(packageId);
////        employee.setLastName("Fadatare");
//
//        // Create project1
//        Plants plants = new Plants();
//        plants.setPlantId(plantId);
//
////        // Create project2
////        Project project1 = new Project();
////        project1.setTitle("Content Management System");
//
//        // employee can work on two projects,  Add project references in the employee
////        packages.getPlants().add(plants);
////        employee.getProjects().add(project1);
//
//        // Add employee reference in the projects
////        plants.getPackages().add(packages);
////        project1.getEmployees().add(employee);
//
////        employeeRepository.save(employee);
////    }
		return null;
	}

	@Override
	public void createNew(Packages packages) {
//		packages.setTimestamp(new Timestamp(new Date().getTime()));
//		packageRepo.save(packages);
		
//		Packages packages1 = new Packages();
//		 Plants plants = new Plants();
//		packages1.getPackages().add(plants);
//		plants.getPlant().add(packages1);
//		packageRepo.save(packages1);
	}

//	@Override
//	public Packages assignPlantToPackage(int packageId, int plantId) {
//		
//		Set<Plants>setPlants=null;
//		Packages packages=packageRepo.findByPackageId(packageId);
//		Plants plant=plantRepo.findByPlantId(plantId);
//		setPlants=packages.getPlants();
//		setPlants.add(plant);
//		packages.setPlants(setPlants);
//		
//		return packageRepo.save(packages);
//	}

	@Override
	public Packages addpackages(Packages packages) {
		// TODO Auto-generated method stub
		return null;
	}

}
