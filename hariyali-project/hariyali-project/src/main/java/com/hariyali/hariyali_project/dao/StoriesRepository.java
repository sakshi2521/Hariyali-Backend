package com.hariyali.hariyali_project.dao;

import java.util.List;
import java.util.Map;

import com.hariyali.hariyali_project.entity.Packages;
import com.hariyali.hariyali_project.entity.Report;
import com.hariyali.hariyali_project.entity.Stories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StoriesRepository extends JpaRepository<Stories, Integer> {
	
	Stories findByStoriesId(Integer storiesId);
	
	Stories findByName(String name);
	
	public Page<Stories> findAllByOrderByStoriesIdDesc(Pageable paging);
	
	@Query(value = "SELECT * FROM hariyalidb.stories where user_id=?", nativeQuery = true)
	public List<Stories> getAllStoryDataByUserId(int userId);

	
	
}
