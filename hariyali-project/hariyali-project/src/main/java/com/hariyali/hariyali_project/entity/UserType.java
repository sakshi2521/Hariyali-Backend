package com.hariyali.hariyali_project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="roles")
public class UserType {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer usertypeId;

	@Column(name="role_name")
	private String usertypeName;

	//bi-directional many-to-one association to Usermst
		@JsonIgnore
		@OneToMany(mappedBy="userRole")
		private List<Users> usermsts;

		
}
