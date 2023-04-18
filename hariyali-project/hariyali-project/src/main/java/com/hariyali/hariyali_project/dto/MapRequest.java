package com.hariyali.hariyali_project.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapRequest {
	
	    private Integer id;
	    
	    private String activity;

	    private Long lat;
	   
	    private Long lng;
	   
	    private Long distance;
	   
	    private Date timestamp;
	   
	    private String session_id;
	    
	    private int transactionId;

		
}
