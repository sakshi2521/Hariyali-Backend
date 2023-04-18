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
public class PackageResponse<T> {
	
	private String status;
	private HttpStatusCode statusCode;
	private T result;
	private String message;
	
	
}
