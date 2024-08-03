package com.project.cafe.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class cafeUtils {
    private cafeUtils() {

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
    
    public static String getUUID() {
    	Date date = new Date();
    	long time = date.getTime();
    	return "BILL-"+time;
    }
    
    public static JSONArray getJsonArrayFromString(String data) throws JSONException {
        if (data != null && !data.isEmpty()) {
            return new JSONArray(data); 
        }
        return new JSONArray(); 
    }

    
    public static Map<String, Object> getMapFromJson(String data) {
    	if(Strings.isNotEmpty(data))
    		return new Gson().fromJson(data, new TypeToken<Map<String, Object>>(){    			
    		}.getType());
    	return new HashMap<>();
    }
}
