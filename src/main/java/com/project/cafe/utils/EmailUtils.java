package com.project.cafe.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailUtils {
	@Autowired
	private JavaMailSender emailSender;
	
	@Value("${app.host:localhost:8080}")
	private String URL;

	public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setFrom("zedcastaneda@gmail.com");
	    log.info(to);
	    message.setTo(to);
	    message.setSubject(subject);
	    message.setText(text);
	    
	    if (list != null && list.size() > 0)
	    	message.setCc(getCcArray(list));
	    
	    emailSender.send(message);
	    log.info("*****Email Sent*****");
	}
	
	private String[] getCcArray(List<String> ccList) {
		String[] cc = new String[ccList.size()];		
		
		for (int i = 0; i < ccList.size(); i++) {
			cc[i] = ccList.get(i);
		}
		
		return cc;
	}
	
	public void forgotMail(String to, String subject, Integer otp) throws MessagingException{
		log.info("*****Email sending...*****");
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom("zedcastaneda@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);		
	    String htmlMsg = "<p><b>Your Login details for Cafe Management System</b><br>" +
                "<b>Email: </b>" + to + "<br>" +
                "<b>OTP: </b>" + otp + "<br>" +
                "<b>Note: </b>The validity of the OTP is 5 minutes only.<br>" +
                "<a href=\"http://" + URL + "/changePassword\">Click here to login</a></p>";
		message.setContent(htmlMsg,"text/html");
		emailSender.send(message);
		log.info("*****Email Sent*****");
	}
	
}
