package com.hariyali.hariyali_project.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.http.HttpStatusCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transaction")
@Data
@FilterDef(name = "deletedQuestionFilter", parameters = @ParamDef(name = "isDeleted",type = Boolean.class))
@Filter(name = "deletedQuestionFilter", condition = "deleted = :isDeleted")

public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private int myTransactionId;
	

	@Column(name = "transaction_des")
	private String Transactiondescription;
	
	@Column(name = "package_id")
	private int packageId;
	
	@Column(name = "package_name")
	private String packageName;
	
	@Column(name = "package_des")
	private String packageDes;
	
	@Column(name = "package_price")
	private int packagePrice; 
	
	@Column(name = "on_behalf")
	private String NameOnTree;
	
	@Column(name = "gifted")
	private Boolean giftTree = Boolean.FALSE;
	
	@Column(name = "custom_gift_message")
	private String customGiftMsg;
	
	@Column(name = "recepient_name")
	private String recepientName;
	
	@Column(name = "recepient_addr")
	private String recepientAddr;
	
	@Column(name = "recepient_phone")
	private String recepientContact;
	
	@Column(name = "recepient_email")
	private String recepientEmail;
	
	@Column(name = "want_benifit")
	private Boolean Benifit = Boolean.FALSE;
	
	@Column(name = "pancard_no")
	private String PancardNo;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "wish_amount")
	private double wishAmount;
	
	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "payment_id")
	private String paymentId;
	
	@Column(name = "payment_holder_name")
	private String paymentHolderName;
	
	@Column(name = "recept_no")
	private String receiptNo;
	
	@Column(name = "transaction_status")
	private String transactionStatus;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "updated_at")
	private Date updatedAt; 
	
	@Column(name="created_by")
	private String createdBy;
	
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private Users user;

	
	
}
