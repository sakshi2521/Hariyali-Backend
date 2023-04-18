package com.hariyali.hariyali_project.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.entity.FileContent;

public interface FileContentService {

	public JwtResponse<FileContent> saveFile(MultipartFile file);

	JwtResponse<Map<String, Object>> getAllFileDocument(int pageNo, int pageSize);

	FileContent getFileDocumentByDocId(long fileId);
	
}
