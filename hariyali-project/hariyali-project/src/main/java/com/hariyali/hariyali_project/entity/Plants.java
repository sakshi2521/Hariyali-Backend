package com.hariyali.hariyali_project.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="plants")
public class Plants {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plant_id")
	private int plantId;
	
	@Column(name = "title", length = 20)
	private String title; 
	
	@Column(name = "quantity")
	private double Quantity;
	
	@Column(name = "total")
	private double Total;
	
	@Column(name = "cost")
	private double cost;
	
	@Column(name = "status")
	private boolean status;

//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//                },
//                mappedBy = "plant")
//	 private Set plant = new HashSet<>();
	
//	@ManyToMany(fetch = FetchType.LAZY,mappedBy="plants"
//          )
//	@JsonBackReference
//	private Set<Packages>packages;

	
    
}
