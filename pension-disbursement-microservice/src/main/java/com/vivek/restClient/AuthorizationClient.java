package com.vivek.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "AuthorizationService")
public interface AuthorizationClient {
	
	// validation of jwt token with authorization micro-service
	
	@GetMapping("/authorize")
	public Boolean authorization(@RequestHeader("Authorization") String token);

}
