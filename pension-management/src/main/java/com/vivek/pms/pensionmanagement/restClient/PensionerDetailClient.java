package com.vivek.pms.pensionmanagement.restClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.vivek.pms.pensionmanagement.model.PensionerDetail;
import com.vivek.pms.pensionmanagement.model.TransactionDetail;

@FeignClient(name = "pensioner-detail")
public interface PensionerDetailClient {

	@GetMapping("/allPensionerDetails")
	public List<PensionerDetail> getAllPensionerDetails();

	@GetMapping("/allTransactionDetails")
	public List<TransactionDetail> getAllPensionTransactionDetails();

}
