package com.hariyali.hariyali_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

	
	private double amount;
	private int packageId;
	private String displayedNameTree;
	private boolean giftTree;
	private String recepientName;
	private String recepientAddress;
	private String recepientContact;
	private String recepientEmail;
	private boolean benifit;
	private String CustomGiftMessage;
	private String pancardDetails;
	private String paymentHolderName;
	private int userId;
	
	
		
}
