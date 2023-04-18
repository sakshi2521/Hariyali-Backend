package com.hariyali.hariyali_project.service;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.dto.MapDto;
import com.hariyali.hariyali_project.dto.MapRequest;
import com.hariyali.hariyali_project.dto.MapResponse;
import com.hariyali.hariyali_project.entity.MapEntity;

public interface MapService {
	
	public Map<String, Object> getAllMapDetails(int pageNo, int pageSize);
	
	public MapEntity getByMapId(int mapId);
	
	
	public MapResponse<MapEntity> saveMap(MapRequest mapDetails);
	
	public List<MapDto> getByMapDetails(String id);
	

}
