package com.vivek.pms.authorizationservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vivek.pms.authorizationservice.exception.ResourceNotFound;
import com.vivek.pms.authorizationservice.model.User;
import com.vivek.pms.authorizationservice.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/*loadbyUserName function loads user from the repository 
	 * returns UserDetails 
	 */

	
	//loading user name from user database passing to spring provided UserDetails  
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
		log.info("START - loadUserByUsername");
		User user = userRepository.findByUserName(username);
		log.info("END - loadUserByUsername");
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
		
		}catch(Exception e)
		{
			log.error("ERROR-username not found");
			throw new ResourceNotFound("User by the given username not found");
		}
		
	}

}
