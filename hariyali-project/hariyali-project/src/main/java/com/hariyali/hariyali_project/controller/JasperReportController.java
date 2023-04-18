package com.hariyali.hariyali_project.controller;

import java.io.FileNotFoundException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hariyali.hariyali_project.dao.UsersRepository;
import com.hariyali.hariyali_project.service.JasperReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/v1/")
public class JasperReportController {
	
	@Autowired
	private UsersRepository userRepo;
	
	@Autowired
	private JasperReportService jasperReportService ;
	

	@PostMapping("generateReciptPdf")
	public String generateReciptPdf(@RequestParam(name="reciptNo") String reciptNo) throws FileNotFoundException, JRException
	{
		System.out.println("reciptn"+reciptNo);
		jasperReportService.generatePdf(reciptNo);
		return "Ok";
	}
	
	@PostMapping("generateCertificate")
	public String generateCertificate(@RequestParam(name="certiNo") String certiNo,Principal principle) throws FileNotFoundException, JRException
	{
		int userId = this.userRepo.findByDonorId(principle.getName()).getUserId();
		jasperReportService.generateCertificate(certiNo, userId);
		return "Certificate generated Successfully for certificate No :"+certiNo;
	}
	
	

}
