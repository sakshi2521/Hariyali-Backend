package com.hariyali.hariyali_project.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="donar_form_details")
public class DonarDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "detailId")
	private int detailId;
	
	
	@Column(name = "DonarName")
	private String donarName; 
	
	@Column(name = "financial_year")
	private Date financial_year;
	
	@Column(name = "assesment_year")
	private Date assesment_year;
	
	
	@ManyToOne
	@JoinColumn(name="file_id")
	private FileContent file;


	
	
	

}
