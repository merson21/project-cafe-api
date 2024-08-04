package com.project.cafe.restImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.rest.DashboardRest;
import com.project.cafe.service.DashboardService;

@RestController
public class DashboardRestImpl implements DashboardRest{
	
	@Autowired
	DashboardService dashboardService;

	@Override
	public ResponseEntity<Map<String, Object>> getcount() {
		try {
			return dashboardService.getcount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new HashMap<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
