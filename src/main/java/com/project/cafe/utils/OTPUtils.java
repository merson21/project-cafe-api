package com.project.cafe.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OTPUtils {
	@Autowired
	EmailUtils emailUtils;	
	
	private class OTPDetails {
		private Integer otp;
		private long expirationTimeStamp;
		private Integer attempt;
		
		public OTPDetails(Integer otp, long expirationTimeStamp, Integer attempt) {
			this.otp = otp;
			this.expirationTimeStamp = expirationTimeStamp;
			this.attempt = attempt;
		}

		public Integer getOtp() {
			return otp;
		}

		public Integer getAttempt() {
			return attempt;
		}

		public long getExpirationTimeStamp() {
			return expirationTimeStamp;
		}				
	}
	
	private static final Map<String, OTPDetails> otpMap = new HashMap<>();
	
	private void addOTP(String email, Integer otp, long expirationTimeStamp) {
		otpMap.put(email, new OTPDetails(otp, expirationTimeStamp, 3)); //3 OTP Attempt only
	}
		
	private Integer getOTP(String email) {
		OTPDetails details = otpMap.get(email);
		return (details != null) ? details.getOtp() : null;
	}
	
	private Long getExpirationTimeStamp(String email) {
		OTPDetails details = otpMap.get(email);
		return (details != null) ? details.getExpirationTimeStamp() : null;
	}
	
	private Integer getAttempt(String email) {
		OTPDetails details = otpMap.get(email);
		return (details != null) ? details.getAttempt() : null;
	}
	
    private void updateAttempt(String email) {
        OTPDetails existingDetails = otpMap.get(email);
        if (existingDetails != null) {
            otpMap.put(email, new OTPDetails(existingDetails.getOtp(), existingDetails.getExpirationTimeStamp(), existingDetails.getAttempt()-1));
        } else {
            System.out.println("OTPDetails not found for email: " + email);
        }
    }
	
	private void removeOTP(String email) {
		otpMap.remove(email);
	}
		
	public void generateOTP(String email) {
		try {
			OTPUtils OTPManager = new OTPUtils();
			long currentTimestamp = System.currentTimeMillis() / 1000L;
			long expirationTimestamp = currentTimestamp + 300; // 5 mins  
			
			Random random = new Random();
			int otp = 100000 + random.nextInt(900000);		
			OTPManager.addOTP(email, otp, expirationTimestamp);
			emailUtils.forgotMail(email, "Credentials by Cafe Management System", otp);				
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean validateOTP(String email, Integer otp) {
	    OTPUtils OTPManager = new OTPUtils();
	    long currentTimestamp = System.currentTimeMillis() / 1000L;

	    Integer storedOTP = OTPManager.getOTP(email);
	    log.info("Validating OTP for email: " + email);
	    log.info("Stored OTP: " + storedOTP + ", Provided OTP: " + otp);

	    if (storedOTP != null) {
	        if (storedOTP.equals(otp)) {
	            long expirationTime = OTPManager.getExpirationTimeStamp(email);
	            log.info("OTP matched. Expiration time: " + expirationTime + ", Current time: " + currentTimestamp);
	            if (currentTimestamp <= expirationTime) {
	            	log.info("OTP is valid and not expired");
	                 OTPManager.removeOTP(email);  
	                return true;
	            } else {
	            	log.info("OTP has expired");
	                OTPManager.removeOTP(email);
	            }
	        } else {
	        	if(OTPManager.getAttempt(email) >= 1) {
		        	updateAttempt(email);
		        	log.info("OTP does not match");
	        	} else {
	                OTPManager.removeOTP(email);
	        		log.info("You Reach Maximum attempt");
	        	}
	        }
	    } else {
	    	log.info("No OTP found for this email");
	    }
	    return false;
	}
	
	
}
