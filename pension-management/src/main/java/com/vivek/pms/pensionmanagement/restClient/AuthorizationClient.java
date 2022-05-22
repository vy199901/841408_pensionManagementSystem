package com.vivek.pms.pensionmanagement.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vivek.pms.pensionmanagement.model.AuthRequest;

@FeignClient(name = "authorization-service")
public interface AuthorizationClient {

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception;

	@GetMapping("/authorize")
	public Boolean authorization(@RequestHeader("Authorization") String token);

}
