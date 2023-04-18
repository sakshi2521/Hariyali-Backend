package com.hariyali.hariyali_project.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hariyali.hariyali_project.entity.Report;
import com.hariyali.hariyali_project.entity.Transaction;
import com.hariyali.hariyali_project.entity.Users;

public interface TransactionSRepository extends JpaRepository<Transaction,Integer> {

	Transaction findByOrderId(String string);

	//get all transaction receipts by user_id
	@Query(value = "SELECT my_transaction_id as transactionId,payment_holder_name as paymentHolderName,"+
	"package_id as packageId,package_name as packageName,package_price as packagePrice,"+
	"wish_amount as wishAmount, amount as amountPaid ,on_behalf as onBehalfOf ,"+
	"recepient_name as giftedTo, recepient_addr as receipientAddress , recepient_phone as receipientContact , recepient_email as receipientEmail ,"+
	"order_id as orderId ,payment_id as paymentId ,transaction_status as transactionStatus ,recept_no as receiptNo,"+
	"created_at as donationDate,created_by as donorId "+
	"FROM hariyalidbletest.transaction where user_id=? order by donationDate desc", nativeQuery = true)
	public List<Map<String,String>> getAllTransactionDataByUserId(int userId);
	
	//get all transaction
	public List<Transaction> findAllByOrderByMyTransactionIdDesc();
	
	// donors list
	@Query(value = "select * from ( select t.created_by as donorId,t.payment_holder_name as paymentHolderName ,"+
				"t.created_at as donationDate, t.amount as amount \r\n"
					+ "from hariyalidbletest.transaction t  where t.created_at = "+
					"(select max(tr.created_at) from hariyalidbletest.transaction tr where t.user_id= tr.user_id )) as res\r\n"
					+ "group by paymentHolderName order by donationDate desc;", nativeQuery = true)
	public List<Map<String,String>> donorList();

	
	//leader board
	@Query(value="select t.user_id as regestrationId,t.created_by as donorId,t.payment_holder_name as paymentHolderName ,sum(t.amount) as totalDonation "+
	"from transaction t left join user_master u on t.user_id=u.user_id "+
	"group by regestrationId ,donorId,paymentHolderName order by totalDonation desc",nativeQuery = true)
	public List<Map<String,String>> totalDonationByDonor();

	
	@Query(value = "SELECT my_transaction_id as transactionId,payment_holder_name as paymentHolderName,"+
			"package_id as packageId,package_name as packageName,package_price as packagePrice,"+
			"wish_amount as wishAmount, amount as amountPaid ,on_behalf as onBehalfOf ,"+
			"recepient_name as giftedTo, recepient_addr as receipientAddress , recepient_phone as receipientContact , recepient_email as receipientEmail ,"+
			"order_id as orderId ,payment_id as paymentId ,transaction_status as transactionStatus ,recept_no as receiptNo,"+
			"created_at as donationDate,created_by as donorId "+
			"FROM hariyalidbletest.transaction where recept_no=? order by donationDate desc", nativeQuery = true)
	public List<Map<String,String>> getAllTransactionDataByReciptNo(String reciptNo);
	
	
	
	@Query(value = "SELECT  t.created_at as donationDate,t.amount as amount,t.recept_no as receiptNo,c.cno as certificateNo from hariyalidbletest.transaction t left join hariyalidbletest.donoation_certificate c on t.my_transaction_id=c.transaction_id where t.user_id=? and t.deleted=0\r\n"
			+ "", nativeQuery = true)
	public List<Map<String,String>> donationTableOfUser(int userId);
	
	
	
	
	@Query(value=DashboardQuery.donationOfAllDonorQuery	,	nativeQuery=true)
	public Double getTotalDonationOfAllDonors();
	
	
	@Query(value=DashboardQuery.donationOfSpecificUserQuery	,	nativeQuery=true)
	public Double getTotalDonationOfSpecificUser(long userId);
	
	
}
