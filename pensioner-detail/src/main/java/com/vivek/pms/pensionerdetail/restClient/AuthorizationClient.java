package com.vivek.pms.pensionerdetail.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authorization-service")
public interface AuthorizationClient {

	/* Validating jwt token with Authorization Microservice */
	@GetMapping("/authorize")
	public Boolean authorization(@RequestHeader("Authorization") String token);

}
