package com.vivek.pms.pensionerdetail.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PensionTransactionDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int transactionId;       
	private String aadhaarNumber;          //Aadhaar Number
	private double transactionAmount;      //TransactionAmount = PensionAmount - BankCharge
	private String accountNumber;          //Bank Account Number
	private String transactionTimestamp;   //Transaction Time-Stamp
	
}
