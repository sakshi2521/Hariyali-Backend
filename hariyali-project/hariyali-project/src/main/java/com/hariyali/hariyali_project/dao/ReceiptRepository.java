package com.hariyali.hariyali_project.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.hariyali.hariyali_project.entity.Report;
import com.hariyali.hariyali_project.entity.Users;

@Repository
public interface ReceiptRepository extends JpaRepository<Report,Integer> {

	Page<Report> findAllByOrderByReceiptIdDesc(Pageable paging);

	Report findByUser(Users user);

	@Query(value = "SELECT * FROM hariyalidb.user_reciept where user_id=?", nativeQuery = true)
	public List<Report> getAllTransactionDataByUserId(int userId);
	
}
