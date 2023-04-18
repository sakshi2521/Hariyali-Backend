package com.hariyali.hariyali_project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hariyali.hariyali_project.entity.DonationCertificate;

public interface CertificateDao extends JpaRepository<DonationCertificate,Integer>{

	
	//all certificates of user
	
	@Query(value="select dc.cid,dc.cno as certificateNo,dc.donor_name as paymentHolderName,dc.donation_amount as amountPaid,dc.donation_date as donationDate,dc.donor_place as donarAddress, dc.transaction_id ,t.recept_no as receiptNo,t.user_id  from donoation_certificate dc left join transaction t on \r\n"
			+ "dc.transaction_id=t.my_transaction_id where  t.user_id=?;",nativeQuery=true)
	public List<Map<String,String>> getAllCertificatesByUserId(int userId);
	
	@Query(value="select dc.cid,dc.donor_name as paymentHolderName,dc.donation_amount as amountPaid,dc.donation_date as donationDate,dc.donor_place donarAddress, dc.transaction_id,t.recept_no as receiptNo ,t.user_id from donoation_certificate dc left join transaction t on \r\n"
			+ "dc.transaction_id=t.my_transaction_id where t.user_id=? and dc.cno=?;\r\n"
			+ "",nativeQuery=true)
	public List<Map<String,String>> getByCertificateNo(int userId,String certificateNo);
	
	
}
