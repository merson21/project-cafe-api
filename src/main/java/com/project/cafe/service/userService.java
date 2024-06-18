package com.project.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.project.cafe.wrapper.UserWrapper;

public interface userService {

	ResponseEntity<String> signUp(Map<String, String> requestMap);

	ResponseEntity<String> login(Map<String, String> requestMap);
	
	ResponseEntity<List<UserWrapper>> getAlluser();
	
	ResponseEntity<String> update(Map<String, String> requestMap);

}
