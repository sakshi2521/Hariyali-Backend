package com.hariyali.hariyali_project.service;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.PackageResponse;
import com.hariyali.hariyali_project.dto.PackagesRequest;
import com.hariyali.hariyali_project.dto.StoriesRequest;
import com.hariyali.hariyali_project.dto.StoriesResponse;
import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Report;
import com.hariyali.hariyali_project.entity.Stories;

import jakarta.servlet.http.HttpServletRequest;

public interface StoriesService {
	
	public Map<String, Object> getAllStories(int pageNo, int pageSize);
	
	public Stories getBystoriesId(int storiesId);
	
	public  JwtResponse<Stories> saveStories(StoriesRequest stories,HttpServletRequest request);
	
	public Stories getByname(String name);

	public JwtResponse<Stories> updateStories(String name, StoriesRequest stories, HttpServletRequest request);

	public JwtResponse<Stories> deleteStories(String name);

	public List<Stories> getAllStoryDataByUserId(int userId);

}
