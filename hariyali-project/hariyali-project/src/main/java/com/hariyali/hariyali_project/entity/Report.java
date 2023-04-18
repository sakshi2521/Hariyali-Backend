package com.hariyali.hariyali_project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_reciept")
public class Report {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receipt_id")
	private int receiptId;
	
	@Column(name = "name_on_tree")
	private String NameOnTree;
	
	@Column(name = "gift_tree")
	private Boolean giftTree = Boolean.FALSE;
	
	
	@Column(name = "recepient_name")
	private String recepientName;
	
	@Column(name = "recepient_addr")
	private String recepientAddr;
	
	@Column(name = "recepient_contact")
	private String recepientContact;
	
	@Column(name = "benifit")
	private Boolean Benifit = Boolean.FALSE;
	
	@Column(name = "amount")
	private double amount;
	
	
	@Column(name = "pancard_no")
	private String pancardNo;
	
	@Column(name = "gift_certificate_msg")
	private String customGiftMsg;
	
	@Column(name = "package_name")
	private String packageName;
	
	@Column(name = "package_des")
	private String packageDes;
	
	
	@Column(name = "package_id")
	private String packageId;
	
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;
	
	
	

}
