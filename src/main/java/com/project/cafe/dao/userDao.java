package com.project.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.cafe.POJO.user;
import com.project.cafe.wrapper.UserWrapper;

import jakarta.persistence.NamedQuery;

public interface userDao extends JpaRepository<user, Integer> {
	
	@Query(value = "select * from user where email = ?1", nativeQuery = true)
	user findByEmailId(String email);

	@Query(value = "SELECT new com.project.cafe.wrapper.UserWrapper(u.id, u.name, u.email, u.contactNumber, u.status) FROM user u WHERE u.role='user'")
	List<UserWrapper> getAllUser();

	@Query(value = "SELECT email FROM user WHERE role='admin'", nativeQuery = true)
	List<String> getAllAdmin();
		
	@Modifying
	@Transactional
	@Query(value = "UPDATE user SET status=?1 WHERE id=?2", nativeQuery = true)
	Integer updateStatus(String status, Integer id);


}
