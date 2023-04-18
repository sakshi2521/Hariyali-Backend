package com.hariyali.hariyali_project.service;

import com.hariyali.hariyali_project.dto.JwtRequest;
import com.hariyali.hariyali_project.dto.JwtResponse;

public interface JwtService {

	
	//public JwtResponse<String> adminLogin(JwtRequest request);
	
	public JwtResponse<String> login(JwtRequest request);

	public JwtResponse<String> resetPassword(String token, String password);
	
	public JwtResponse<String> sendEmailPassword(String email);
	
	public JwtResponse<String> logout(JwtRequest request, String token);

//	public JwtResponse<String> forgotPassword(String userCd,String email);

}
