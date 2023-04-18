package com.hariyali.hariyali_project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hariyali.hariyali_project.dao.CertificateDao;
import com.hariyali.hariyali_project.dto.DonorInfoRequest;
import com.hariyali.hariyali_project.service.DonationCertificateInterface;

@Service
public class DonationCertificateServiceImpl implements DonationCertificateInterface{

	@Autowired
	private CertificateDao certificateRepo;
	@Override
	public List<DonorInfoRequest> getAllCertificateByUserId(int userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<DonorInfoRequest> donorInfo = objectMapper.convertValue(this.certificateRepo.getAllCertificatesByUserId(userId),new TypeReference<List<DonorInfoRequest>>() {});
		 return donorInfo;
	}
	
	
	@Override
	public List<DonorInfoRequest> getCertificateByNumber(int userId, String certificateNo) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<DonorInfoRequest> donorInfo = objectMapper.convertValue(this.certificateRepo.getByCertificateNo(userId, certificateNo),new TypeReference<List<DonorInfoRequest>>() {});
		 return donorInfo;
	}

	
}
