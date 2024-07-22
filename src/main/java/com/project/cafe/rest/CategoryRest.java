package com.project.cafe.rest;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.cafe.POJO.Category;

@RequestMapping(path = "/category")
public interface CategoryRest {
	
	
	@PostMapping(path = "/add")
	ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestMap);
	
	@GetMapping(path = "/get")
	ResponseEntity<List<Category>> getAllCategory(@RequestBody(required = false) String filterValue);

}