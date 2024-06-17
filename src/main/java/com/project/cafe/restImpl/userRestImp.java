package com.project.cafe.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cafe.constents.cafeConstants;
import com.project.cafe.rest.userRest;
import com.project.cafe.service.userService;
import com.project.cafe.utils.cafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class userRestImp implements userRest{

    @Autowired
    userService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
    	log.info("triggered signup");
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return cafeUtils.getResponseEntity(cafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
   @GetMapping("/hello")
   public String hello() {
       return "Hello, World!";
   }

}
