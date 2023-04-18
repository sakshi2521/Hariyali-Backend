package com.hariyali.hariyali_project.controller;

import com.hariyali.hariyali_project.dto.JwtRequest;
import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.service.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/")
public class JwtController {

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(JwtController.class);
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private JwtService jwtService;
	
	
	@Value("${user.account.locktime}")
	private Integer lockTime;
	
	@GetMapping("welcome")
	public ResponseEntity<?> welcome() {
		return new ResponseEntity<>("welcome",HttpStatus.OK);
	}
	//login user
	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody JwtRequest loginRequest) throws Exception {
//		JwtResponse response = new JwtResponse();
		//System.out.println("In login controller");
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
			JwtResponse<String> response = jwtService.login(loginRequest);
			 if(response.getStatus()=="Error") {
				 return new ResponseEntity<>(response.getMessage(), response.getStatusCode());
			 }
			 
			 return new ResponseEntity<>(response, response.getStatusCode());
		} catch (Exception e) {
			logger.error("Error occurred - ", e);
			return new ResponseEntity<>("Invalid Username or Password", HttpStatus.BAD_REQUEST);
		}
		
	}
	

	//Reset Password
	@PostMapping("resetPassword")
	public ResponseEntity<?> resetPassword(@RequestParam String password,@RequestParam String token) {
		try {
		JwtResponse<String> response = jwtService.resetPassword(token,password);
			return new ResponseEntity<>(response,response.getStatusCode());
		}catch(Exception e){
			logger.error("Error occurred - ", e);
		return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//sending mail
	@PostMapping("sendEmail")
	public ResponseEntity<?> sendEmailResetPassword(@RequestParam String email) {	
		try {
		JwtResponse<String> res=this.jwtService.sendEmailPassword(email);
		return new ResponseEntity<>(res,res.getStatusCode());
		}catch(Exception e) {
		logger.error("Error occurred - ", e);
		return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
		}
	}

	

	@PostMapping("logout")
	public ResponseEntity<?> logout(@RequestBody JwtRequest loginRequest,HttpServletRequest request) {
		logger.info("logout method called Successfully..");
		String token = request.getHeader("Authorization").substring(7);
		try {
			JwtResponse<?> res = this.jwtService.logout(loginRequest, token);
			logger.debug("Logged out successfully - {}", loginRequest.getUsername());
			return new ResponseEntity<>(res,res.getStatusCode());
		}
		catch(Exception e) {
			logger.error("Error occurred - ", e);
			return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
		}
	}

}
