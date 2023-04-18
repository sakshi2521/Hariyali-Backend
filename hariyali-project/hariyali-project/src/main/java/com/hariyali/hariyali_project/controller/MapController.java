package com.hariyali.hariyali_project.controller;

import java.util.List;

import com.hariyali.hariyali_project.dao.MapRepository;
import com.hariyali.hariyali_project.dto.MapDto;
import com.hariyali.hariyali_project.dto.MapRequest;
import com.hariyali.hariyali_project.dto.MapResponse;
import com.hariyali.hariyali_project.entity.MapEntity;
import com.hariyali.hariyali_project.exception.CustomException;
import com.hariyali.hariyali_project.service.MapService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MapController {
	
	@Autowired
	private MapService mapService;
	
	@Autowired
	private MapRepository mapRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@PostMapping("/AddMap")
	public ResponseEntity<Object> addMapDetails(@RequestBody MapRequest mapRequest) {
		MapResponse<MapEntity> mapDetails = null;
		try {
			mapDetails = this.mapService.saveMap(mapRequest);
			logger.debug("Saving Map- {}", mapRequest.getActivity());
		} catch (Exception e) {
			return new ResponseEntity<Object>(new CustomException(e.getMessage(), e.getMessage()),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(mapDetails, HttpStatus.OK);
	}

	
	@GetMapping("/map/{id}")
	public List<MapDto> getByMapDetails(@PathVariable String id) 
	{
		
		return this.mapService.getByMapDetails(id);
	
	}
	
}
