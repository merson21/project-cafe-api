package com.project.cafe.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class cafeUtils {
    private cafeUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
