package com.hariyali.hariyali_project.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonarDetailsDto {
	
	private int detailId;
	
	private String donarName; 
	
	private int fileId;
	
	private int user_id;
	
	private String file_name;
	
	private Date assesment_year;
	
	private Date financial_year;

}
