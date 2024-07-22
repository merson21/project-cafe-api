package com.project.cafe.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.project.cafe.JWT.JwtFilter;
import com.project.cafe.POJO.Category;
import com.project.cafe.constents.cafeConstants;
import com.project.cafe.dao.CategoryDao;
import com.project.cafe.service.CategoryService;
import com.project.cafe.utils.cafeUtils;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		try {			
			if(jwtFilter.isAdmin()) {
				if(validateCategoryRequestMap(requestMap, false)) {
					categoryDao.save(getCategoryFromMap(requestMap,false));
					return cafeUtils.getResponseEntity("Category Added Successfully",HttpStatus.OK);
				}
			}else {
				return cafeUtils.getResponseEntity(cafeConstants.UNAUTHORIZED_ACCESS,HttpStatus.BAD_REQUEST); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cafeUtils.getResponseEntity(cafeConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateCategoryRequestMap(Map<String, String> requestMap, boolean validateId) {
		if(requestMap.containsKey("name")) {
			if((requestMap.containsKey("id") && validateId) || !validateId) {
				return true;
			}
		}
		return false;
	}
	
	private Category getCategoryFromMap(Map<String, String> requestMap, boolean isAdd) {
		Category category = new Category();
		
		if(isAdd) {
			category.setId(Integer.parseInt(requestMap.get("id")));
		}
		category.setName(requestMap.get("name"));
		
		return category;
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
		try {
			if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
				return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
			}
			return new ResponseEntity<List<Category>>(categoryDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); 
		}		
		return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
