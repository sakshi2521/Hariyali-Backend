package com.hariyali.hariyali_project.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="file_content")
@FilterDef(name = "deletedBulletinFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedBulletinFilter", condition = "deleted = :isDeleted")

public class FileContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="file_id")
	private long fileId;
	
	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_content")
	private String fileContent;
	
	@Column(name="file_path")
	private String filePath;

	@Column(name = "deleted")
	private Boolean isDeleted = Boolean.FALSE;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createdAt")
	private Date createdAt;
	
	@Column(name="createdBy")
	private String createdBy;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updatedAt")
	private Date updatedAt;
	
	@Column(name="updatedBy")
	private String updatedBy;

		
	
}
