package com.vivek.pms.processpension.service;

import org.springframework.stereotype.Service;

import com.vivek.pms.processpension.model.PensionerDetail;
import com.vivek.pms.processpension.model.ProcessPensionResponse;

@Service
public class ProcessPensionService {

	public ProcessPensionResponse getPensionDetails(PensionerDetail pensionerDetail) {
		
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse(
				pensionerDetail,
				pensionerDetail.getPensionType().equalsIgnoreCase("Self")?((0.8*pensionerDetail.getSalaryEarned())+pensionerDetail.getAllowances()):((0.5*pensionerDetail.getSalaryEarned())+pensionerDetail.getAllowances()),
			    pensionerDetail.getBankDetail().getBankType().equalsIgnoreCase("Private")?550:500);
		
		return processPensionResponse;
	}
	
}
