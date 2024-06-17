package com.project.cafe.serviceImpl;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cafe.POJO.user;
import com.project.cafe.constents.cafeConstants;
import com.project.cafe.dao.userDao;
import com.project.cafe.service.userService;
import com.project.cafe.utils.cafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class userServiceImpl implements userService {
	
	@Autowired
	userDao userDao;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
	    log.info("Inside signup {}", requestMap);
	    try {	
		    if (validateSignUpMap(requestMap)) {
		    	user user = userDao.findByEmailId(requestMap.get("email"));
		        if (Objects.isNull(user)) {
		        	userDao.save(getUserFromMap(requestMap));
		        	return cafeUtils.getResponseEntity("Successfully Registered.", HttpStatus.OK);
		        } else {
		            return cafeUtils.getResponseEntity("Email already exists.", HttpStatus.BAD_REQUEST);
		        }
		    } else {
		        return cafeUtils.getResponseEntity(cafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
		    }
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
	    return cafeUtils.getResponseEntity(cafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	private boolean validateSignUpMap(Map<String, String> requestMap) {
	    if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
	            && requestMap.containsKey("email") && requestMap.containsKey("password")) {
	        return true;
	    }
	    return false;
	}
	
	private user getUserFromMap(Map<String, String> requestMap) {
		user user = new user();
	    user.setName(requestMap.get("name"));
	    user.setContactNumber(requestMap.get("contactNumber"));
	    user.setEmail(requestMap.get("email"));
	    user.setPassword(requestMap.get("password"));
	    user.setStatus("false");
	    user.setRole("user");
	    return user;
	}


}
