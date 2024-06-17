package com.project.cafe.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface userService {

	ResponseEntity<String> signUp(Map<String, String> requestMap);

}
