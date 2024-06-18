package com.project.cafe.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.cafe.dao.userDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service	
public class customUserDetailsService implements UserDetailsService{
	
	@Autowired
	userDao userDao;

	private com.project.cafe.POJO.user userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Load inside loadUserByUsername {}", username);
		userDetail = userDao.findByEmailId(username);
	    if(!Objects.isNull(userDetail)) {
	        return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
	    } else {
	        throw new UsernameNotFoundException("User not found.");
	    }
	}
	
	public com.project.cafe.POJO.user getUserDetail(){
		
		return userDetail;
	}


}
