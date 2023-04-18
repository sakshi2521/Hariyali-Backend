package com.hariyali.hariyali_project.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hariyali.hariyali_project.dao.CertificateDao;
import com.hariyali.hariyali_project.dao.UsersRepository;
import com.hariyali.hariyali_project.dto.DonorInfoRequest;
import com.hariyali.hariyali_project.service.DonationCertificateInterface;

@RestController
@RequestMapping("/api/v1/")
public class DonationCertificateController {

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private DonationCertificateInterface certificateService;
	
	//get transaction by currently logged-In user
		@GetMapping("getAllCertificatesByUser")
		public List<DonorInfoRequest> getAllUserCertificate(Principal principle){
			System.err.println("hello");

			int userId=this.usersRepository.findByDonorId(principle.getName()).getUserId();
			System.out.println("userId"+userId);
			return this.certificateService.getAllCertificateByUserId(userId);
		}
		
		//get transaction by currently logged-In user
				@GetMapping("getCertificateByNumber/{certificateNo}")
				public List<DonorInfoRequest> getCertificateByUser(@PathVariable String certificateNo,Principal principle){
					System.err.println("hello");

					int userId=this.usersRepository.findByDonorId(principle.getName()).getUserId();
					System.out.println("userId"+userId);
					return this.certificateService.getCertificateByNumber(userId, certificateNo);
				}
			
	
}
