package com.hariyali.hariyali_project.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="token_login_user")
@Getter
@Setter
public class TokenLoginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="token_id")
	private Long id;
	
	@Column(name="token")
	private String token;
	
	@Column(name="flag")
	private boolean flag;
	
	@Column(name="username")
	private String username;
	
	@Column(name="last_updated_on")
	private Date lastUpdatedOn;
	
	
	
}
