package com.hariyali.hariyali_project.entity;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name="packages")
public class Packages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
	private int packageId;
	
	@Column(name = "title", length = 20)
	private String title; 
	
	@Column(name = "description", length = 50)
	private String description;
	
	@Column(name = "image_name")
	private String imageName;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "created_at")
	private Date created_At;
	
	@Column(name = "updated_at")
	private Date updated_At;
	
	@Column(name = "created_by", length = 20)
	private String Created_By;
	
	@Column(name = "updated_by",length = 20)
	private Date Updated_By;
	
	@Column(name = "status", length = 20)
	private String status;
	
	@Column(name = "deleted")
	private Boolean Isdeleted=Boolean.FALSE;

	
	
}
