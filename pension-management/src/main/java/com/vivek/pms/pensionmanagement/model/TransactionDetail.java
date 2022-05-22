package com.vivek.pms.pensionmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetail {

	private String aadhaarNumber;           //AadhaarNumber 
	private double transactionAmount;       //TransactionAmount = PensionAmount - BankCharge 
	private String accountNumber;           //Bank Account Number
	private String transactionTimestamp;    //Transaction TimeStamp
	
}
