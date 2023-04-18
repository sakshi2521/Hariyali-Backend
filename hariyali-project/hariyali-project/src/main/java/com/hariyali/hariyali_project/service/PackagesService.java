package com.hariyali.hariyali_project.service;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PackagesRequest;
import com.hariyali.hariyali_project.dto.UsersRequest;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Users;

import jakarta.servlet.http.HttpServletRequest;

public interface PackagesService {
	
	public Map<String, Object> getAllPackages(int pageNo, int pageSize);
	
	public void createNew(Packages packages);

//public Packages assignPlantToPackage(int packageId,int plantId);
	
	public Packages getByPackageId(int packageId);
	
	public Packages getByPackageTitle(String packageTitle);

	public PackageResponse<Packages> savePackage(PackagesRequest pkg);

	public PackageResponse<Packages> updatePackage(String userCode, PackagesRequest pkg, HttpServletRequest request);

	public PackageResponse<Packages> deletePackage(String userCode);

	public Packages CreateManyTable(int packageId, int plantId);

	public Packages addpackages(Packages packages);

	
	

}
