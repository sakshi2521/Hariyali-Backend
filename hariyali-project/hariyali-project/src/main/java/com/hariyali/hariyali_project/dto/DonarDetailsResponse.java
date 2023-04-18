package com.hariyali.hariyali_project.dto;

import java.util.Date;

import com.hariyali.hariyali_project.entity.FileContent;

import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonarDetailsResponse<T> {

	private String status;
	private HttpStatusCode statusCode;
	private T result;
	private String message;
	
	
}
