package com.hariyali.hariyali_project.serviceImpl;

import java.util.List;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hariyali.hariyali_project.dao.TransactionSRepository;
import com.hariyali.hariyali_project.dto.DonorInfoRequest;
import com.hariyali.hariyali_project.entity.Transaction;
import com.hariyali.hariyali_project.service.TransactionService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionSRepository transactionRepo;
	@Override
	public Transaction getTransactionByUser(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DonorInfoRequest> getAllReportDataByUserId(int userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<DonorInfoRequest> donorInfo = objectMapper.convertValue(this.transactionRepo.getAllTransactionDataByUserId(userId),new TypeReference<List<DonorInfoRequest>>() {});
		 return donorInfo;
	}

	@Override
	public List<Transaction> GetAllDonors() {
		return this.transactionRepo.findAllByOrderByMyTransactionIdDesc();
	}

	@Override
	public List<DonorInfoRequest> getDonorInfoList() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<DonorInfoRequest> donorInfo = objectMapper.convertValue(this.transactionRepo.donorList(),new TypeReference<List<DonorInfoRequest>>() {});
		 return donorInfo;
	}

	@Override
	public List<DonorInfoRequest> getAllDonations() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<DonorInfoRequest> totalDonation =objectMapper.convertValue(this.transactionRepo.totalDonationByDonor(),new TypeReference<List<DonorInfoRequest>>() {});
		
		return totalDonation;
		
		
	}

	@Override
	public List<DonorInfoRequest> getAllReportDataByReciptNo(String reciptNo) {
		
			System.out.println(reciptNo);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			List<DonorInfoRequest> donorInfo = objectMapper.convertValue(this.transactionRepo.getAllTransactionDataByReciptNo(reciptNo),new TypeReference<List<DonorInfoRequest>>() {});
//			System.out.println(donorInfo);
			return donorInfo;	
		
	}

	@Override
	public List<DonorInfoRequest> getUserDonationTable(int userId) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		List<DonorInfoRequest> donorInfo = objectMapper.convertValue(this.transactionRepo.donationTableOfUser(userId),new TypeReference<List<DonorInfoRequest>>() {});
//		System.out.println(donorInfo);
		return donorInfo;	
	}

	

	

}

