package com.hariyali.hariyali_project.service;

import com.hariyali.hariyali_project.dto.DashboardDto;
import com.hariyali.hariyali_project.dto.JwtResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface DashboardService {

	public JwtResponse<DashboardDto> getDashboardData(HttpServletRequest request);
}
