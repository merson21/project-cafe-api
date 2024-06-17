package com.project.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.project.cafe.POJO.user;

public interface userDao extends JpaRepository<user, Integer> {
	
	user findByEmailId(@Param("email") String email);

}
