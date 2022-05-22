package com.vivek.pms.pensionerdetail.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivek.pms.pensionerdetail.exception.ResourceNotFoundException;
import com.vivek.pms.pensionerdetail.model.BankDetail;
import com.vivek.pms.pensionerdetail.model.PensionerDetail;
import com.vivek.pms.pensionerdetail.repository.PensionerDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PensionerDetailService {

	@Autowired
	PensionerDetailRepository pensionerDetailRepository;

	/*
	 * Reading all pensioner details from csv file and inserting all details to database
	 */
	@PostConstruct
	public void savePensionerData() {
		log.info("START - Loading all Pensioner Data from CSV File - Service ");

		List<PensionerDetail> pensionerDetailList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/details.csv"));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				PensionerDetail pensionerDetail = new PensionerDetail();
				pensionerDetail.setAadhaarNumber(data[0]);
				pensionerDetail.setName(data[1]);
				pensionerDetail.setDateOfBirth(data[2]);
				pensionerDetail.setPan(data[3]);
				pensionerDetail.setSalaryEarned(Double.parseDouble(data[4]));
				pensionerDetail.setAllowances(Double.parseDouble(data[5]));
				pensionerDetail.setPensionType(data[6]);
				pensionerDetail.setBankDetail(new BankDetail(data[7], data[8], data[9]));
				pensionerDetailList.add(pensionerDetail);
			}
			br.close();
			} catch (IOException e) {
			log.error("Exception - savePensionerData - Service");
			throw new ResourceNotFoundException("Error in Adding Pensioner Detail from CSV File - Service");
		}

		pensionerDetailRepository.saveAll(pensionerDetailList);
		log.info("END - Finished loading of Pensioner Data from CSV File");
	}
	
	public List<PensionerDetail> fetchAllPensionerDetails() {
		log.info("START - fetchAllPensionerDetails -Service");
		log.info("END - fetchAllPensionerDetails -Service");
		return pensionerDetailRepository.findAll();
	}
	
	public PensionerDetail findbyAadhaarNumber(String aadhaarNumber) {
		log.info("START - findbyAadhaarNumber -Service");
		log.info("END - findbyAadhaarNumber -Service");
		return pensionerDetailRepository.findById(aadhaarNumber).orElseThrow(() -> new ResourceNotFoundException("Aadhaar number not found"));
	}

}
