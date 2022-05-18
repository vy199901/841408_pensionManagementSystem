package com.vivek.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vivek.model.ProcessPensionInput;
import com.vivek.model.ProcessPensionResponse;

@FeignClient(name = "PensionDisburesmentService")
public interface PensionDisbursementClient {
	
	@PostMapping("/disbursePension")
	public ProcessPensionResponse getPensionDisbursement(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput);

}

