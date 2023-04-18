package com.hariyali.hariyali_project.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="donoation_certificate")
@Getter
@Setter
public class DonationCertificate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cid")
	private int certificateId;
	
	
	@Column(name = "cno")
	private String certificateNo; 
	
	
	@Column(name = "donor_name")
	private String DonorName;

	@Column(name = "donation_amount")
	private double DonationAmount;
	
	@Column(name = "donation_date")
	private Date donationDate;
	
	@Column(name = "donor_place")
	private String donorPlace;

	@Column(name = "transaction_id")
	private int transactionId;

	


	
	
	
	
}
