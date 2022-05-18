package com.vivek.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vivek.model.PensionerDetail;


@FeignClient(name = "PensionerDetailService")
public interface PensionerDetailClient {
	
	//getting pensioner details from Pensioner detail microservice
	
	@GetMapping("/pensionerDetail/{aadhaarNumber}")
	public PensionerDetail findByAadhaarNumber(@RequestHeader("Authorization") String token1,  @PathVariable String aadhaarNumber);

	@GetMapping("/allDetails")
	public List<PensionerDetail> getAllDetail();
}
