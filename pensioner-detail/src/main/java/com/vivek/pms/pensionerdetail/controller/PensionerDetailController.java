package com.vivek.pms.pensionerdetail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.pms.pensionerdetail.model.PensionTransactionDetail;
import com.vivek.pms.pensionerdetail.model.PensionerDetail;
import com.vivek.pms.pensionerdetail.restClient.AuthorizationClient;
import com.vivek.pms.pensionerdetail.service.PensionTransactionDetailService;
import com.vivek.pms.pensionerdetail.service.PensionerDetailService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PensionerDetailController {

	@Autowired
	PensionerDetailService pensionerDetailService;
	@Autowired
	PensionTransactionDetailService pensionTransactionDetailService;
	@Autowired
	AuthorizationClient authorizationClient;
	
	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		log.info("START Pensioner Detail microservice welcome");
		log.info("END - Pensioner Detail microservice welcome");
		return ResponseEntity.ok("Welcome to Pensioner Detail Microservice");
	}

	/* Fetch Pensioner Detail from provided Aadhaar Number */
	@GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
	public PensionerDetail findByAadhaarNumber(@RequestHeader("Authorization") String token,
			@PathVariable String aadhaarNumber) throws Exception {
		log.info("START - findByAadhaarNumber -Controller");

		if (authorizationClient.authorization(token)) {
			PensionerDetail pensionerDetail = pensionerDetailService.findbyAadhaarNumber(aadhaarNumber);
			log.info("END - findByAadhaarNumber -Controller");
			return pensionerDetail;
		} else {
			log.error("EXCEPTION - findByAadhaarNumber -Controller");
			throw new Exception("Invalid Token");
		}
	}

	/* Fetch all Pensioner Details */
	@GetMapping("/allPensionerDetails")
	public List<PensionerDetail> getAllPensionerDetails() {
		log.info("START - getAllPensionerDetails -Controller");
		log.info("END - getAllPensionerDetails -Controller");
		return pensionerDetailService.fetchAllPensionerDetails();
	}

	/* Fetch all Pension Transaction Details */
	@GetMapping("/allTransactionDetails")
	public List<PensionTransactionDetail> getAllPensionTransactionDetails() {
		log.info("START - getAllPensionerDetails -Controller");
		log.info("END - getAllPensionerDetails -Controller");
		return pensionTransactionDetailService.fetchAllPensionTransactionDetails();
	}

	/* Save Pension Transaction Details */
	@PostMapping("/saveTransactionDetail")
	public boolean savePensionTransactionDetail(@RequestHeader("Authorization") String token,
			@RequestBody PensionTransactionDetail pensionTransactionDetail) throws Exception {
		log.info("START - savePensionTransactionDetail -Controller");

		if (authorizationClient.authorization(token)) {
			boolean saveFlag = pensionTransactionDetailService.savePensionTransactionDetail(pensionTransactionDetail);
			log.info("END - savePensionTransactionDetail -Controller");
			return saveFlag;
		} else {
			log.error("EXCEPTION - savePensionTransactionDetail -Controller");
			throw new Exception("Invalid Token");
		}
	}

}
