package com.hariyali.hariyali_project.service;

import java.util.Map;

import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PackagesRequest;
import com.hariyali.hariyali_project.dto.PlantRequest;
import com.hariyali.hariyali_project.dto.PlantResponse;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Plants;

import jakarta.servlet.http.HttpServletRequest;

public interface PlantsService {
	public Map<String, Object> getAllPlant(int pageNo, int pageSize);

	public Plants getByPlantId(int packageId);
	
	public Plants getByPlantTitle(String plantTitle);

	public PlantResponse savePlant(PlantRequest plant);

	public JwtResponse<Plants> updateplants(String userCode, PlantRequest plant, HttpServletRequest request);

	public JwtResponse<Plants> deleteplants(String userCode);
}
