package com.vivek.pms.pensionmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.pms.pensionmanagement.exception.ResourceNotFoundException;
import com.vivek.pms.pensionmanagement.model.AuthRequest;
import com.vivek.pms.pensionmanagement.model.PensionerDetail;
import com.vivek.pms.pensionmanagement.model.ProcessPensionInput;
import com.vivek.pms.pensionmanagement.model.ProcessPensionResponse;
import com.vivek.pms.pensionmanagement.model.Token;
import com.vivek.pms.pensionmanagement.model.TransactionDetail;
import com.vivek.pms.pensionmanagement.restClient.AuthorizationClient;
import com.vivek.pms.pensionmanagement.restClient.PensionerDetailClient;
import com.vivek.pms.pensionmanagement.restClient.ProcessPensionClient;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
public class ManagementController {

	@Autowired
	private AuthorizationClient authorizationClient;
    @Autowired
	private ProcessPensionClient processPensionClient;
    @Autowired
	private PensionerDetailClient pensionerDetailClient;

	@GetMapping("/")
	public String display() {
		return "Pension Management Portal working";
	}

	/* Validating token with Authorization Microservice */
	@PostMapping("/token")
	public ResponseEntity<?> doLogin(@RequestBody AuthRequest authRequest) {
		log.info("START - doLogin");
		String token = null;
		try {
			token = authorizationClient.generateToken(authRequest);
		} catch (Exception e) {
			log.error("EXCEPTION - doLogin");
			throw new ResourceNotFoundException("Token can't be Generated");
		}
		log.debug(token);
		log.info("END - doLogin");
		return ResponseEntity.ok(new Token(token));
	}

	/* Getting Pensioner Details from pensioner details micro service */
	@GetMapping("/details")
	public List<PensionerDetail> getAllPensionerDetail() {
		log.info("START - getAllPensionerDetail");
		List<PensionerDetail> pensionerDetail = null;
		try {
			pensionerDetail = pensionerDetailClient.getAllPensionerDetails();
		} catch (Exception e) {
			log.error("EXCEPTION - getAllPensionerDetail");
			throw new ResourceNotFoundException("Pensioner detail list not found");
		}
		log.info("END - getAllPensionerDetail");
		return pensionerDetail;
	}
	
	/* Getting Processed Pension Details from Pensioner Details MicroService */
	@GetMapping("/transactionDetails")
	public List<TransactionDetail> getAllTransactionDetail() {
		log.info("START - getAllTransactionDetail");
		List<TransactionDetail> transactionDetails = null;
		try {
			transactionDetails = pensionerDetailClient.getAllPensionTransactionDetails();
		} catch (Exception e) {
			log.error("EXCEPTION - getAllTransactionDetail");
			throw new ResourceNotFoundException("Transaction detail list not found");
		}
		log.info("END - getAllTransactionDetail");
		return transactionDetails;
	}

	/* Calculating Pensioner Detail from Process Pension Microservice */
	@PostMapping("/pensionDetail")
	public ResponseEntity<ProcessPensionResponse> getPensionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput pensionerInput) {
		log.info("START - getPensionDetail");
		try {
			authorizationClient.authorization(token);
		} catch (Exception e) {
			log.error("EXCEPTION - Getting Pension-Detail");
			throw new ResourceNotFoundException("Enter a valid Token");
		}
		ProcessPensionResponse processPensionResponse = processPensionClient.calculatePensionAmount(token, pensionerInput);
		log.info("END - Getting Pension-Detail");
		return ResponseEntity.ok(processPensionResponse);

	}

	/* Return Success or Failure based on pension Transaction */
	@PostMapping("/processPension")
	public boolean getStatusCode(@RequestHeader(name = "Authorization") String token,
			@RequestBody TransactionDetail transactionDetail) {
		log.info("START - getStatusCode");
		try {
			authorizationClient.authorization(token);
		} catch (Exception e) {
			log.error("EXCEPTION - getStatusCode");
			throw new ResourceNotFoundException("Enter a valid Token");
		}
		log.info("END - getStatusCode");
		return processPensionClient.saveTransactionDetail(token, transactionDetail);
	}

}
