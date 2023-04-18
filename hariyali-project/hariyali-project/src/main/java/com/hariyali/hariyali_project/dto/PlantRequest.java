package com.hariyali.hariyali_project.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantRequest {
	
	private String title; 
	private double quantity;
	private double total;
	private double cost;
	private boolean status;


	//private List<Packages>packages=new ArrayList();

}
