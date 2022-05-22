package com.vivek.pms.pensionmanagement.restClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vivek.pms.pensionmanagement.model.ProcessPensionInput;
import com.vivek.pms.pensionmanagement.model.ProcessPensionResponse;
import com.vivek.pms.pensionmanagement.model.TransactionDetail;

@FeignClient(name = "process-pension")
public interface ProcessPensionClient {

	@PostMapping("/ProcessPension")
	public ProcessPensionResponse calculatePensionAmount(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput);

	@PostMapping("/ProcessPensionTransaction")
	public boolean saveTransactionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody TransactionDetail transactionDetail);

}
