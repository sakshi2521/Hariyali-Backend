package com.hariyali.hariyali_project.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequest {
	@JsonInclude(value =Include.NON_DEFAULT)
	private int userId;
	@NotBlank(message = "First name should not be null")
	private String firstName; 
	@NotBlank(message = "Last name should not be null")
	private String lastName;
	private String userName;
	private String userCode;
	
	@NotNull
	private String userPhone;
	
	@NotNull
	//@Email(message = "Email should be valid", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String userEmail;
	//private String userAddress;
	private String userDesignation;
	private String userCompanyName; 
	private String userPassword ;
	private String userStatus;
	@JsonInclude(value =Include.NON_DEFAULT)
	private int roleId;
	private String userAddress;
	//Getters and Setters
	private String donorId;
	private String role;
		
}
