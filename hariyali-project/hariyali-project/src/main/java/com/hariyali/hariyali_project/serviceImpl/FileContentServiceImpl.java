package com.hariyali.hariyali_project.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hariyali.hariyali_project.dao.FileContentRepository;
import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.entity.FileContent;
import com.hariyali.hariyali_project.service.FileContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import jakarta.persistence.EntityManager;

@Service
public class FileContentServiceImpl implements FileContentService{

	private static final Logger logger = LoggerFactory.getLogger(FileContentServiceImpl.class);

	@Autowired
	private FileContentRepository fileContentRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public JwtResponse<FileContent> saveFile(MultipartFile file) {
		JwtResponse<FileContent> res = new JwtResponse<FileContent>();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(df.format(new Date()));
		String dateTime=df.format(new Date());
		FileContent fContent = new FileContent();
		//String bulletinDocList = "";
		try {
			File newfile = new File("D:\\hariyali\\", file.getOriginalFilename());
			if (!newfile.exists()) {
				FileOutputStream fileOutStream = new FileOutputStream(newfile);
				fileOutStream.write(file.getBytes());
				fileOutStream.flush();
				fileOutStream.close();
			}
			String path = "D:\\hariyali\\" + file.getOriginalFilename();
			String fileDocContent = file.getContentType();
			String name=file.getOriginalFilename();
			String[] name1 = name.split("[.]", 0);
		       String Bulletinname=name1[0]+dateTime+"."+name1[1];
		       fContent.setFileName(Bulletinname);
		       fContent.setFileContent(fileDocContent);
		       fContent.setFilePath(path);
		       fileContentRepository.save(fContent);
//			int bulletinId = bulletin.getBulletinId();
//			bulletinDocList += bulletinId + "";

		} catch (IOException e) {
			logger.error("Error saving document", e);
		}
		res.setResult(fContent);
		res.setStatus("SUCCESS");
		res.setMessage("Pdf Uploaded Sucessfully");
		res.setStatusCode(HttpStatus.CREATED);
		return res;
	}

	@Override
	public JwtResponse<Map<String, Object>> getAllFileDocument(int pageNo, int pageSize) {
		
		JwtResponse<Map<String, Object>> res = new JwtResponse<>();
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("deletedBulletinFilter");
		filter.setParameter("isDeleted", false);
		Pageable paging = PageRequest.of(pageNo - 1, pageSize);
		Page<FileContent> fContent = this.fileContentRepository.findAll(paging);
		List<FileContent> fileContentEntities = fContent.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("FileContEntities", fileContentEntities);
		response.put("currentPage", fContent.getNumber());
		response.put("totalItems", fContent.getTotalElements());
		response.put("totalPages", fContent.getTotalPages());
		res.setResult(response);
		res.setStatus("SUCCESS");
		res.setStatusCode(HttpStatus.OK);
		session.disableFilter("deletedBulletinFilter");
		if (fContent.hasContent()) {
			return res;
		} else {
			return null;
		}
	}

	@Override
	public FileContent getFileDocumentByDocId(long fileId) {
		return this.fileContentRepository.findByfileId(fileId);
	}

	
}
