package com.vivek.pms.authorizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.pms.authorizationservice.exception.ResourceNotFound;
import com.vivek.pms.authorizationservice.model.AuthRequest;
import com.vivek.pms.authorizationservice.service.UserDetailService;
import com.vivek.pms.authorizationservice.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthorizationController {

	private JwtUtil jwtUtil;

	private UserDetailService userDetailService;

	private AuthenticationManager authenticationManager;

	
	@Autowired
	public AuthorizationController(JwtUtil jwtUtil, UserDetailService userDetailService,
			AuthenticationManager authenticationManager) {

		this.jwtUtil = jwtUtil;
		this.userDetailService = userDetailService;
		this.authenticationManager = authenticationManager;
	}

	//starting message 
	
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		log.info("START authorization microservice welcome");
		log.info("END - authorization microservice welcome");
		return ResponseEntity.ok("Wecome to security application");
	}

	//jwt token authentication using user name and password
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		log.info("START - generateToken");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

		} catch (Exception e) {
			log.error("EXCEPTION - generateToken");
			throw new ResourceNotFound("user not found");
		}

		log.info("END - generateToken");
		return ResponseEntity.ok(jwtUtil.generateToken(authRequest.getUserName()));
	}
	
	
	//validtiion of the generated jwt token to access '/authorize' endpoint

	@GetMapping("/authorize")
	public ResponseEntity<?> authorization(@RequestHeader("Authorization") String token1) {

		log.info("START - authorization");
		String token = token1.substring(7);

		UserDetails user = userDetailService.loadUserByUsername(jwtUtil.extractUsername(token));

		if (jwtUtil.validateToken(token, user)) {
			log.info("END - authorization");
			return new ResponseEntity<>(true, HttpStatus.OK);
		} else {
			log.info("END - authorization");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}

	}

}
