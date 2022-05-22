package com.vivek.pms.processpension.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vivek.pms.processpension.model.PensionerDetail;
import com.vivek.pms.processpension.model.TransactionDetail;

@FeignClient(name="pensioner-detail")
public interface PensionerDetailClient {

	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail findByAadhaarNumber(@RequestHeader("Authorization") String token,  @PathVariable String aadhaarNumber);

	@PostMapping("/saveTransactionDetail")
	public boolean savePensionTransactionDetail(@RequestHeader("Authorization") String token, @RequestBody TransactionDetail transactionDetail);

}
