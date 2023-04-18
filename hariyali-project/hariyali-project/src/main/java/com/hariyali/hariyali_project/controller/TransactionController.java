package com.hariyali.hariyali_project.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.Constants;
import com.hariyali.hariyali_project.confing.JwtHelper;
import com.hariyali.hariyali_project.dao.CertificateDao;
import com.hariyali.hariyali_project.dao.TransactionSRepository;
import com.hariyali.hariyali_project.dao.UsersRepository;
import com.hariyali.hariyali_project.dto.DonorInfoRequest;
import com.hariyali.hariyali_project.dto.PaymentRequest;
import com.hariyali.hariyali_project.entity.DonationCertificate;
import com.hariyali.hariyali_project.entity.Transaction;
import com.hariyali.hariyali_project.entity.Users;
import com.hariyali.hariyali_project.service.TransactionService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionservice;
	
//	@Autowired
//	private UsersRepository usersRepository;
	
	@Autowired
	private TransactionSRepository transactionRepo;

	@Autowired
	private CertificateDao certificateRepo;
	
	@Autowired
	private UsersRepository userRepo;
	
	
	@Autowired
	private JwtHelper jwtHelper;
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	
	@PostMapping("create_order")
	public String createOrder(@RequestBody PaymentRequest data,Principal principle) throws RazorpayException {
		
		
		System.out.println(data);
		double amt = data.getAmount();
		int rec= 235425+1;
		
		var client = new RazorpayClient("rzp_test_2UqpQxRJsWKk7Y","XA74bJ5aJastFQgevadhnr7F");
		
		JSONObject options = new JSONObject();
		options.put("amount", amt*100);
		options.put("currency", "INR");
		options.put("receipt", "txn"+"_"+rec);
		
		
		//create order
		
		Order order = client.Orders.create(options);
		System.out.println(order);   
			
		// saving data on server
		Transaction transaction = new Transaction();
		if(data.getPackageId()==1)
		{
			transaction.setPackageId(1);
			transaction.setPackageName("Five Hundred");
			transaction.setPackageDes("Package of Rs. 500/-");
			transaction.setPackagePrice(500);
			//transaction.setWishAmount((Integer) null);
		}
		else if(data.getPackageId()==2)
		{
			transaction.setPackageId(2);
			transaction.setPackageName("One  Thousand");
			transaction.setPackageDes("Package of Rs. 1000/-");
			transaction.setPackagePrice(1000);
			//transaction.setWishAmount((Integer) null);
		}else if(data.getPackageId()==3)
		{
			transaction.setPackageId(3);
			transaction.setPackageName("Two Thousand");
			transaction.setPackageDes("Package of Rs. 500/-");
			transaction.setPackagePrice(2000);
			//transaction.setWishAmount((Integer) null);
			
		}
		else if(data.getPackageId()==4)
		{
			transaction.setPackageId(4);
			transaction.setPackageName("Five Thousand");
			transaction.setPackageDes("Package of Rs. 5000/-");
			transaction.setPackagePrice(5000);
			//transaction.setWishAmount((Integer) null);
		}
		else if(data.getPackageId()==5)
		{
			transaction.setPackageId(5);
			transaction.setPackageName("Seven Thousand");
			transaction.setPackageDes("Package of Rs. 7000/-");
			transaction.setPackagePrice(7000);
			//transaction.setWishAmount((Integer) null);
			
		}else if(data.getPackageId()==6)
		{
			transaction.setPackageId(6);
			transaction.setPackageName("Ten Thousand");
			transaction.setPackageDes("Package of Rs. 10000/-");
			transaction.setPackagePrice(10000);
			//transaction.setWishAmount((Integer) null);
		}
		else 
		{
			//transaction.setPackageId((Integer)null);
			transaction.setPackageName(null);
			transaction.setPackageDes(null);
			//transaction.setPackagePrice((Integer) null);
			transaction.setWishAmount(data.getAmount());
		}
		
		transaction.setGiftTree(data.isGiftTree());
		if(data.isGiftTree()==false)
		{
			transaction.setRecepientAddr(this.userRepo.findByDonorId(principle.getName()).getUserAddress());
			transaction.setRecepientContact(this.userRepo.findByDonorId(principle.getName()).getUserPhone());
			transaction.setRecepientName(null);
			transaction.setRecepientEmail(null);
			transaction.setCustomGiftMsg(null);
		}
		else 
		{
			transaction.setRecepientName(data.getRecepientName());
			transaction.setRecepientEmail(data.getRecepientEmail());
			transaction.setRecepientContact(data.getRecepientContact());
			transaction.setCustomGiftMsg(data.getCustomGiftMessage());
			transaction.setRecepientAddr(null);
		} 
		
		transaction.setBenifit(data.isBenifit());
		
		if(data.isBenifit()==true)
		{
			transaction.setPancardNo(data.getPancardDetails());
		}
		else 
		{
			transaction.setPancardNo(null);
		}
		
		//transaction.setAmount(order.get("amount")+"");
		
		transaction.setAmount(amt);
		
		transaction.setOrderId(order.get("id"));
		transaction.setPaymentId(null);
		transaction.setNameOnTree(data.getDisplayedNameTree());
		
		//transaction.setUser(this.userRepo.findByDonorId(principle.getName()));
		Users user=this.userRepo.findByDonorId(principle.getName());
		
		transaction.setTransactionStatus("created");
		transaction.setReceiptNo(Constants.receiptNumber);
		System.out.println("username"+this.userRepo.findByDonorId(principle.getName()).getUserId());
		transaction.setUser(user);
		transaction.setPaymentHolderName(user.getFirstName()+" "+user.getLastName());
		transaction.setCreatedAt(new Date());
		transaction.setCreatedBy(user.getDonorId());
		
		
		System.out.println("payment_id"+order.get("payment_id")+"");	
		this.transactionRepo.save(transaction);
		
		//certificate details
		DonationCertificate certificate = new DonationCertificate();
		
		certificate.setCertificateNo(Constants.certificateNumber);
		certificate.setDonorName(user.getFirstName()+" "+user.getLastName());
		certificate.setDonationAmount(amt);
		certificate.setDonationDate(new Date());
		certificate.setDonorPlace(user.getUserAddress());
		certificate.setTransactionId(transaction.getMyTransactionId());
		this.certificateRepo.save(certificate);
		return order.toString();

	}

	@PostMapping("update_order")
	public ResponseEntity<?> updateOrder(@RequestBody Map<String,Object> data,Principal principle)
	{
		Transaction transaction = new Transaction();
		transaction.setUser(this.userRepo.findByDonorId(principle.getName()));
		
		 transaction = this.transactionRepo.findByOrderId(data.get("order_id").toString());


		transaction.setPaymentId(data.get("payment_id").toString());
		transaction.setTransactionStatus(data.get("status").toString());
		this.transactionRepo.save(transaction);
		System.out.println(data);
		return ResponseEntity.ok(Map.of("msg","updated"));
	}
	
		//get transaction by currently logged-In user
	@GetMapping("getTransactionReceiptByUser")
	public List<DonorInfoRequest> getTransactionByUserId(HttpServletRequest request){
		

		String token = request.getHeader("Authorization");
		String userCode = jwtHelper.getUsernameFromToken(token.substring(7));
		Users user = this.userRepo.findByDonorId(userCode);
		int userId=user.getUserId();
		
		System.err.println("UserCode :"+userCode);
		System.err.println("UserId :"+userId);
		return this.transactionservice.getAllReportDataByUserId(userId);
	}
	
	// get transaction data by recipt id
	
	@GetMapping("getTransactionReceiptNo")
	public List<DonorInfoRequest> getTransactionByReceiptNo(@RequestParam(name="]") String reciptNo){
		System.out.println("hello");
		return this.transactionservice.getAllReportDataByReciptNo(reciptNo);
	}
	
	
	
	//object mapper-donor list
		@GetMapping("donorList")
		public List<DonorInfoRequest> getTransactionByPaymentId(){
				return this.transactionservice.getDonorInfoList();
			}
		
		//leader board 
		@GetMapping("leaderBoard")
		public List<DonorInfoRequest> getLeaderBoardbyTotalDonation(){
				return this.transactionservice.getAllDonations();
			}
		
		@GetMapping("donationTableUser")
				public List<DonorInfoRequest> getDonationTableByUserToken(HttpServletRequest request){
						
					String token = request.getHeader("Authorization");
					String userCode = jwtHelper.getUsernameFromToken(token.substring(7));
					Users user = this.userRepo.findByDonorId(userCode);
					int userId=user.getUserId();
					
					System.err.println("UserCode :"+userCode);
					System.err.println("UserId :"+userId);
					return this.transactionservice.getUserDonationTable(userId);
					
					}
		
		
}
