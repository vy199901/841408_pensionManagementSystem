package com.vivek.pms.pensionerdetail.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetail {

	private String bankName;		// Bank-Name of Pensioner
	private String accountNumber;	// Account Number of Pensioner
	private String bankType;		// Bank Type of pensioner Eg. Private or Public
	
}
