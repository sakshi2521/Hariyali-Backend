package com.hariyali.hariyali_project.serviceImpl;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hariyali.hariyali_project.dao.PlantRepository;
import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PlantRequest;
import com.hariyali.hariyali_project.dto.PlantResponse;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Plants;
import com.hariyali.hariyali_project.service.PlantsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PlantServiceImpl implements PlantsService{
	
	@Autowired
	private PlantRepository plantRepo;

	@Override
	public Map<String, Object> getAllPlant(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plants getByPlantId(int packageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plants getByPlantTitle(String plantTitle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlantResponse savePlant(PlantRequest plant) {
		ObjectMapper mapper = new ObjectMapper();
		if (this.plantRepo.existsByTitle(plant.getTitle())) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Plant is already exists!");
		}

		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Plants savedPlants = mapper.convertValue(plant, Plants.class);

		double qty=savedPlants.getQuantity();
		double cost=savedPlants.getCost();
		
		double total=qty*cost;
		savedPlants.setTotal(total);
		System.out.println(total);
//		savedPlants.setCreated_At(new Date());
//		savedPackage.setIsdeleted(false);

		savedPlants = this.plantRepo.save(savedPlants);
		PlantResponse response = null;
		if (savedPlants != null) {
			response = mapper.convertValue(savedPlants, PlantResponse.class);
		}
		return response;
	}

	@Override
	public JwtResponse<Plants> updateplants(String userCode, PlantRequest plant, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JwtResponse<Plants> deleteplants(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
