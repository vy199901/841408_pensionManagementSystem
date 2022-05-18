package com.vivek.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vivek.model.PensionDetail;
import com.vivek.model.PensionerDetail;
import com.vivek.model.PensionerInput;

@FeignClient(name = "ProcessPensionService")
public interface ProcessPensionClient {
	
	@PostMapping("/pensionerInput")
	public PensionDetail getPensionDetail(@RequestHeader(name = "Authorization") String token, @RequestBody PensionerInput pensionerInput);

	@GetMapping("/details")
	public List<PensionerDetail> allDetail();
}
