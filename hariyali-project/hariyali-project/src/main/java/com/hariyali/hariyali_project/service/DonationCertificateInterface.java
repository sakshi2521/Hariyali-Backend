package com.hariyali.hariyali_project.service;

import java.util.List;

import com.hariyali.hariyali_project.dto.DonorInfoRequest;

public interface DonationCertificateInterface {

	
	//certificate by user id
		public List<DonorInfoRequest> getAllCertificateByUserId(int userId);
		
		//certificate by certificate number
		
		public List<DonorInfoRequest> getCertificateByNumber(int userId,String certificateNo);
		
		
}
