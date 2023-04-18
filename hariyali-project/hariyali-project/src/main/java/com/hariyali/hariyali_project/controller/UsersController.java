package com.hariyali.hariyali_project.controller;

import java.security.Principal;
import java.util.Map;

import com.hariyali.hariyali_project.confing.JwtHelper;
import com.hariyali.hariyali_project.dao.UsersRepository;
import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.UsersRequest;
import com.hariyali.hariyali_project.entity.Users;
import com.hariyali.hariyali_project.exception.CustomException;
import com.hariyali.hariyali_project.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UsersController {

	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	// Get All users
	@GetMapping("/usersGetAll")
	public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "5000") Integer pageSize) {
		logger.debug("Getting all users");
		Map<String, Object> users = usersService.getUsers(pageNo, pageSize);
		if (users == null) 
		{
			String message = "No users found";
			String details = "Please check that data exists";
			logger.error(message + "," + details);
			return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// get user by username

	@GetMapping("/user-by-email/{userEail}")
	public ResponseEntity<Object> getByUserEmail(@PathVariable String userEail) {
		Users user = usersService.getByUserEmail(userEail);
		if (user == null) {
			String message = "User with user name " + userEail + " not found";
			String details = "Please check user name";
			logger.error(message + "," + details);
			return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}



	// Add User
	@PostMapping("/userAdd")
	public ResponseEntity<?> addUser(@RequestBody UsersRequest userObj,HttpServletRequest request) {
		logger.info("saveUser method called successfully");
		JwtResponse<Users> user = new JwtResponse<>();
		try {
			user = usersService.saveUser(userObj,request);
			logger.info("saveUser method executed successfully");
			return new ResponseEntity<>(user,user.getStatusCode());
		} catch (Exception e) {
			logger.error("error occured"+e);
			}
		user.setMessage("Something Went wrong");
		user.setStatus("Error");
		user.setStatusCode(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// update user
	@PutMapping("/updateuser/{donorId}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "donorId") String donorId,
			@RequestBody UsersRequest user, HttpServletRequest request) {
		JwtResponse<Users> response = new JwtResponse<>();
		try {
			response = this.usersService.updateUser(donorId, user, request);
			return new ResponseEntity<>(response, response.getStatusCode());
		} catch (Exception e) {
			logger.error("error occured" + e);
		}
		response.setMessage("Something Went wrong");
		response.setStatus("ERROR");
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getStatusCode());
	}
	
	

	
	
	// delete user
	@DeleteMapping("/deleteuser/{donorId}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "donorId") String donorId) {
		JwtResponse<Users> response = new JwtResponse<>();
		try {
			response = this.usersService.deleteUserByDonorId(donorId);
			return new ResponseEntity<>(response, response.getStatusCode());
		} catch (Exception e) 
		{
			logger.error("error occured" + e);
		}
	
		response.setMessage("Something Went wrong");
		response.setStatus("ERROR");
		response.setStatusCode(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, response.getStatusCode());
	}
	
	
	//sending mail for donor Id
		@PostMapping("sendEmailForDonorId")
		public ResponseEntity<?> sendEmailDonorId(@RequestParam String email) {	
			try {
			JwtResponse<String> res=this.usersService.sendEmailDonorId(email);
			return new ResponseEntity<>(res,res.getStatusCode());
			}catch(Exception e) {
			logger.error("Error occurred - ", e);
			return new ResponseEntity<>("Something Went Wrong",HttpStatus.BAD_REQUEST);
			}
		}
		
		
		//get Currently logged-In User
		@GetMapping("/currentusername")
		public String currentUserName(Principal principal) {
		        return principal.getName();
		}
		
		
		
		@PutMapping("/activateuser")
		public ResponseEntity<?> activateuser(@RequestParam String userName) {
			JwtResponse<String> response = new JwtResponse<>();
			try {
				response = usersService.updateUserForactivation(userName);
				return new ResponseEntity<>(response, response.getStatusCode());
			} catch (Exception e) {
				logger.error("error occured" + e);
			}
			response.setMessage("Something Went wrong");
			response.setStatus("ERROR");
			response.setStatusCode(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(response, response.getStatusCode());
		}
		
		@GetMapping("/userProfile")
		public UsersRequest getUserProfile(HttpServletRequest request)
		{
			String token = request.getHeader("Authorization");
			String donorId = jwtHelper.getUsernameFromToken(token.substring(7));
			Users user = this.usersRepository.findByDonorId(donorId);
			int userId=user.getUserId();
			
			return this.usersService.getDonorInfo(userId);
			
		}
		
//		public UsersRequest getUserProfile(Principal principle)
//		{
//			int userId=this.usersRepository.findByDonorId(principle.getName()).getUserId();
//			System.out.println("userId"+userId);
//			return this.usersService.getDonorInfo(userId);
//			
//		}
		
		@GetMapping("/TotalNoOfDonors")
		public long getDonorCount()
		{
			return this.usersService.getDonorCunt();
			
		}
	
}
