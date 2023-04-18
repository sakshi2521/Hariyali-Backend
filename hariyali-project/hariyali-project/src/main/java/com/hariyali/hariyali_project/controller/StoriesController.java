package com.hariyali.hariyali_project.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dao.UsersRepository;
import com.hariyali.hariyali_project.dto.JwtResponse;
import com.hariyali.hariyali_project.dto.StoriesRequest;
import com.hariyali.hariyali_project.dto.StoriesResponse;
import com.hariyali.hariyali_project.entity.Stories;
import com.hariyali.hariyali_project.exception.CustomException;
import com.hariyali.hariyali_project.service.StoriesService;

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

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class StoriesController {
	private static final Logger logger = LoggerFactory.getLogger(StoriesController.class);
	
	@Autowired
	private StoriesService storiesService;
	
	
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	// Get All Stories
	@GetMapping("/getAllStories")
	public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "5000") Integer pageSize) {
		System.out.println("in stories controller");
		logger.debug("Getting all stories");
		Map<String, Object> users = this.storiesService.getAllStories(pageNo, pageSize);
		if (users == null) {
			String message = "No stories found";
			String details = "Please check that data exists";
			logger.error(message + "," + details);
			return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	

	// get stories by story Id

			@GetMapping("/stories-by-id/{id}")
			public ResponseEntity<Object> getByStoriesId(@PathVariable String id) {
				Stories stories = this.storiesService.getBystoriesId(Integer.parseInt(id));
				if (stories == null) {
					String message = "Stories with package name " + id + " not found";
					String details = "Please check user name";
					logger.error(message + "," + details);
					return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(stories, HttpStatus.OK);
			}

	
			//get Stories by name

			@GetMapping("/stories-by-name/{name}")
			public ResponseEntity<Object> getByStoriesName(@PathVariable String name) {
				Stories stories = this.storiesService.getByname(name);
				if (stories == null) {
					String message = "stories with name " + name + " not found";
					String details = "Please check the story title";
					logger.error(message + "," + details);
					return new ResponseEntity<Object>(new CustomException(message, details), HttpStatus.NOT_FOUND);
				}
				return new ResponseEntity<>(stories, HttpStatus.OK);
			}
	
			
			
			
			
			// update Stories
			@PutMapping("/updatStories/{name}")
			public ResponseEntity<?> updateStories(@PathVariable(value = "name") String name,
					@RequestBody StoriesRequest stories, HttpServletRequest request) {
				JwtResponse<Stories> response = new JwtResponse<>();
				try {
					response = this.storiesService.updateStories(name, stories, request);
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
			@DeleteMapping("/deleteStories/{name}")
			public ResponseEntity<?> deleteUser(@PathVariable(value = "name") String name) {
				JwtResponse<Stories> response = new JwtResponse<>();
				try {
					response = this.storiesService.deleteStories(name);
					return new ResponseEntity<>(response, response.getStatusCode());
				} catch (Exception e) {
					logger.error("error occured" + e);
				}
				response.setMessage("Something Went wrong");
				response.setStatus("ERROR");
				response.setStatusCode(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(response, response.getStatusCode());
			}

			
			
			// Add Stories
			
			@PostMapping("/AddStories")
			public ResponseEntity<Object> addStories(@RequestBody StoriesRequest storiesRequest,HttpServletRequest request) {
				JwtResponse<Stories> stories = new JwtResponse<>();
				System.err.println("in controller");
				try {
					System.out.println("in try");

					stories = this.storiesService.saveStories(storiesRequest,request);
					return new ResponseEntity<>(stories,stories.getStatusCode());
				} catch (Exception e) {
					System.err.println("in catch");
					
				}
				stories.setMessage("Something Went wrong");
				stories.setStatus("Error");
				stories.setStatusCode(HttpStatus.BAD_REQUEST);
				
				return new ResponseEntity<>(stories, HttpStatus.OK);

			}
			
			
			@GetMapping("/getStoryDataByUserId")
			public List<Stories> getTransactionByUserId(Principal principle){
				System.err.println("hello");

				int userId=this.usersRepository.findByDonorId(principle.getName()).getUserId();
				System.out.println("userId"+userId);
				return this.storiesService.getAllStoryDataByUserId(userId);
			}
			
			
			
	
	
	
	
	
	
}