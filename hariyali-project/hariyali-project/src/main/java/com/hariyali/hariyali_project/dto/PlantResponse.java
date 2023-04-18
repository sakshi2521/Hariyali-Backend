package com.hariyali.hariyali_project.dto;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantResponse {
	
    private int plantId;
	
	
	private String title; 
	
	
	private double cost;
	
	private double quantity;
	
	private double total;
	
	
	private boolean status;

	
	
//	private List<Packages>packages=new ArrayList();



	
}
