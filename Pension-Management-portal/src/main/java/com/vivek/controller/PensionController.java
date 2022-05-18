package com.vivek.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.exception.ResourceNotFoundException;
import com.vivek.model.AuthRequest;
import com.vivek.model.PensionDetail;
import com.vivek.model.PensionerDetail;
import com.vivek.model.PensionerInput;
import com.vivek.model.ProcessPensionInput;
import com.vivek.model.ProcessPensionResponse;
import com.vivek.model.Token;
import com.vivek.restClient.AuthorizationClient;
import com.vivek.restClient.PensionDisbursementClient;
import com.vivek.restClient.ProcessPensionClient;

@RestController
@CrossOrigin(origins = "*")
public class PensionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PensionController.class);

	private AuthorizationClient authorizationClient;

	private ProcessPensionClient processPensionClient;

	private PensionDisbursementClient pensionDisbursementClient;

	@Autowired
	public PensionController(AuthorizationClient authorizationClient, ProcessPensionClient processPensionClient,
			PensionDisbursementClient pensionDisbursementClient) {
		this.authorizationClient = authorizationClient;
		this.processPensionClient = processPensionClient;
		this.pensionDisbursementClient = pensionDisbursementClient;
	}

	// starting message
	@GetMapping("/")
	public String display() {
		return "Pension management working";
	}

	// validating token with authorization microservice

	@PostMapping("/token")
	public ResponseEntity<?> doLogin(@RequestBody AuthRequest authRequest) {
		LOGGER.info("START - doLogin");
		String token = null;
		try {
			token = authorizationClient.generateToken(authRequest);

		} catch (Exception e) {
			LOGGER.error("EXCEPTION - doLogin");
			throw new ResourceNotFoundException("token can't be generated");
		}

		System.out.println(token);
		LOGGER.debug(token);

		LOGGER.info("END - doLogin");
		return ResponseEntity.ok(new Token(token));
	}

	// getting pensioner details from pensioner details micro service

	@GetMapping("/details")
	public List<PensionerDetail> allDetail() {
		LOGGER.info("START - allDetail");
		List<PensionerDetail> pensionerDetail = null;
		try {
			pensionerDetail = processPensionClient.allDetail();
		} catch (Exception e) {
			throw new ResourceNotFoundException("pensioner detail list not found");
		}
		LOGGER.info("END - allDetail");
		return pensionerDetail;

	}

	// calculating pension details for input pensioner details with pensioner micro
	// service
	@PostMapping("/pensionDetail")
	public ResponseEntity<PensionDetail> getPensionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody PensionerInput pensionerInput) {
		LOGGER.info("START - Getting Pension-Detail");
		try {
			authorizationClient.authorization(token);
		} catch (Exception e) {
			LOGGER.error("EXCEPTION - Getting Pension-Detail");
			throw new ResourceNotFoundException("enter a valid token");
		}
		PensionDetail pensionDetail = processPensionClient.getPensionDetail(token, pensionerInput);
		LOGGER.info("END - Getting Pension-Detail");
		return ResponseEntity.ok(pensionDetail);

	}

	// return Success or failure code based on given pension disburse input
	// providing with pension disbursement micro service
	@PostMapping("/processPension")
	public ProcessPensionResponse getStatusCode(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) {
		LOGGER.info("START - getStatusCode");
		try {
			authorizationClient.authorization(token);
		} catch (Exception e) {
			LOGGER.error("EXCEPTION - getStatusCode");
			throw new ResourceNotFoundException("enter a valid token");
		}
		LOGGER.info("END - getStatusCode");
		return pensionDisbursementClient.getPensionDisbursement(token, processPensionInput);
	}

}
