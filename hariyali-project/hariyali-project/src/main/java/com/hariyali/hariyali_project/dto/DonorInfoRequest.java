package com.hariyali.hariyali_project.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
public class DonorInfoRequest {
	

	@JsonInclude(value =Include.NON_DEFAULT)
	private int regestrationId;
	@JsonInclude(value=Include.NON_DEFAULT)
	private int transactionId;
	private String paymentHolderName;
	private String transactionStatus;
	private String donorId;
	
	@JsonInclude(value =Include.NON_DEFAULT)
	private int packageId;
	private String packageName;
	@JsonInclude(value =Include.NON_DEFAULT)
	private int packagePrice;
	@JsonInclude(value =Include.NON_DEFAULT)
	private double wishAmount;
	private String onBehalfOf;
	private String giftedTo;
	private String receipientAddress;
	private String receipientContact;
	private String receipientEmail;
	private String donarAddress;
	private String donarContact;
	@JsonInclude(value=Include.NON_DEFAULT)
	private boolean benifit;
	private String pancardNo;
	private String orderId;
	private String paymentId;
	private String receiptNo;
	private Date donationDate;
	@JsonInclude(value =Include.NON_DEFAULT)
	private double amountPaid;
	@JsonInclude(value =Include.NON_DEFAULT)
	private double amount;
	@JsonInclude(value =Include.NON_DEFAULT)
	private double totalDonation;
	@JsonInclude(value =Include.NON_DEFAULT)
	private int certificateId;
	private String certificateNo;
	
	
	//Getter and Setters 
	
	
	
	
	
}
