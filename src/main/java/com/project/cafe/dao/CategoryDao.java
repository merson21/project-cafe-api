package com.project.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.cafe.POJO.Category;

public interface CategoryDao extends JpaRepository<Category, Integer>{

		@Query(value = "select * from category", nativeQuery = true)
		List<Category> getAllCategory();
}
