package com.vivek.pms.processpension.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.pms.processpension.exception.ProcessPensionException;
import com.vivek.pms.processpension.model.ProcessPensionInput;
import com.vivek.pms.processpension.model.ProcessPensionResponse;
import com.vivek.pms.processpension.model.TransactionDetail;
import com.vivek.pms.processpension.restClient.PensionerDetailClient;
import com.vivek.pms.processpension.service.ProcessPensionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProcessPensionController {
	
	@Autowired
	PensionerDetailClient pensionerDetailClient;
	@Autowired
	ProcessPensionService processPensionService;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		log.info("START Process Pension microservice welcome");
		log.info("END - Process Pension microservice welcome");
		return ResponseEntity.ok("Welcome to Process Pension Microservice");
	}
	
	/*
	 * Generating Pension Amount and Bank Service-Charge from user Input Aadhaar Card
	 */
	@PostMapping("/ProcessPension")
	public ProcessPensionResponse calculatePensionAmount(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) {
		log.info("START - calculatePensionAmount -Controller");
		ProcessPensionResponse processPensionResponse = null;		
		try {
			processPensionResponse = processPensionService.getPensionDetails(
					pensionerDetailClient.findByAadhaarNumber(token, processPensionInput.getAadhaarNumber()));
		} catch (Exception e) {
			log.error("EXCEPTION - calculatePensionAmount -Controller");
			throw new ProcessPensionException("Pensioner Detail not coreect");
		}
		log.info("END - calculatePensionAmount -Controller");
		return processPensionResponse;
	}
	
	/*
	 * Saving Pension Transaction Detail
	 */
	@PostMapping("/ProcessPensionTransaction")
	public boolean saveTransactionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody TransactionDetail transactionDetail) {
		log.info("START - savePensionTransactionDetail -Controller");
		boolean flag = false;
		try {
			flag = pensionerDetailClient.savePensionTransactionDetail(token, transactionDetail);
		} catch (Exception e) {
			log.error("EXCEPTION - savePensionTransactionDetail -Controller");
			throw new ProcessPensionException("Pension Detail not coreect");
		}
		log.info("END - savePensionTransactionDetail -Controller");
		return flag;
	}

}
