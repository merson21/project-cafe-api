package com.project.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.cafe.POJO.Bill;

public interface BillDao extends JpaRepository<Bill, Integer>{

	@Query(value = "SELECT * FROM bill ORDER BY id DESC", nativeQuery = true)
	List<Bill> getAllBills();


	@Query(value = "SELECT * FROM bill WHERE createdby = ?1 ORDER BY id DESC", nativeQuery = true)
	List<Bill> getBillByUserName(String currentUser);

}
