package com.hariyali.hariyali_project.service;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dto.DonorInfoRequest;
import com.hariyali.hariyali_project.entity.Report;
import com.hariyali.hariyali_project.entity.Transaction;

public interface TransactionService {
	
	public Transaction getTransactionByUser(Transaction transaction);

	//transaction by user id
	public List<DonorInfoRequest> getAllReportDataByUserId(int userId);
	
	public List<Transaction> GetAllDonors();
	
	public List<DonorInfoRequest> getDonorInfoList();
	
	public List<DonorInfoRequest> getAllDonations();
	
	public List<DonorInfoRequest> getAllReportDataByReciptNo(String reciptNo);
	
	public List<DonorInfoRequest> getUserDonationTable(int userId);
}
