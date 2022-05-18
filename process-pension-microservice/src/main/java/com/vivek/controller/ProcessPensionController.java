package com.vivek.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.exception.ProcessPensionException;
import com.vivek.model.PensionDetail;
import com.vivek.model.PensionerDetail;
import com.vivek.model.PensionerInput;
import com.vivek.model.ProcessPensionInput;
import com.vivek.model.ProcessPensionResponse;
import com.vivek.restClient.PensionDisbursementClient;
import com.vivek.restClient.PensionerDetailClient;
import com.vivek.service.ProcessPensionService;

@RestController
public class ProcessPensionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPensionController.class);
	private PensionerDetailClient pensionerDetailClient;
	private ProcessPensionService processPensionService;
	private PensionDisbursementClient pensionDisbursementClient;

	@Autowired
	public ProcessPensionController(PensionerDetailClient pensionerDetailClient,
			PensionDisbursementClient pensionDisbursementClient, ProcessPensionService processPensionService) {
		this.pensionerDetailClient = pensionerDetailClient;
		this.pensionDisbursementClient = pensionDisbursementClient;
		this.processPensionService = processPensionService;
	}

	//getting all details from pensioner details micro service
	@GetMapping("/details")
	public List<PensionerDetail> allDetail() {
		LOGGER.info("START - allDetail");
		List<PensionerDetail> pensionerDetail = pensionerDetailClient.getAllDetail();
		LOGGER.info("END - allDetail");
		return pensionerDetail;
	}

	//generating pension detail with pension amount for given user input
	@PostMapping("/pensionerInput")
	public PensionDetail getPensionDetail(@RequestHeader(name = "Authorization") String token,
			@RequestBody PensionerInput pensionerInput) {
		LOGGER.info("START - allDetail");
		PensionDetail pensionDetail = null;
		try {
			pensionDetail = processPensionService.getPensionDetail(
					pensionerDetailClient.findByAadhaarNumber(token, pensionerInput.getAadhaarNumber()),
					pensionerInput);

		} catch (Exception e) {
			LOGGER.error("EXCEPTION - allDetail");
			throw new ProcessPensionException("Pensioner Detail not coreect");
		}
		LOGGER.info("END - allDetail");
		return processPensionService.savePensionDetail(pensionDetail);

	}

	
	//success code in case of valid pension amount 
	@PostMapping("/processPension")
	public ProcessPensionResponse getStatusCode(@RequestHeader(name = "Authorization") String token,
			@RequestBody ProcessPensionInput processPensionInput) {
		LOGGER.info("START - getStatusCode");
		LOGGER.info("END - getStatusCode");
		return pensionDisbursementClient.getPensionDisbursement(token, processPensionInput);
	}

}
