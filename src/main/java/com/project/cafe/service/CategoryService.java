package com.project.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.cafe.POJO.Category;

public interface CategoryService {

	ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

	ResponseEntity<List<Category>> getAllCategory(String filterValue);

}
