package com.vivek.pms.pensionerdetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivek.pms.pensionerdetail.model.PensionTransactionDetail;
import com.vivek.pms.pensionerdetail.repository.PensionTransactionDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PensionTransactionDetailService {

	@Autowired
	PensionTransactionDetailRepository pensionTransactionDetailRepository;
	
	public List<PensionTransactionDetail> fetchAllPensionTransactionDetails() {
		log.info("START - fetchAllPensionTransactionDetails -Service");
		log.info("END - fetchAllPensionTransactionDetails -Service");
		return pensionTransactionDetailRepository.findAll();
	}
	
	public boolean savePensionTransactionDetail(PensionTransactionDetail pensionTransactionDetail) {
		log.info("START - savePensionTransactionDetail -Service");
		log.info("END - savePensionTransactionDetail -Service");
		return pensionTransactionDetailRepository.save(pensionTransactionDetail) != null;
	}
}
