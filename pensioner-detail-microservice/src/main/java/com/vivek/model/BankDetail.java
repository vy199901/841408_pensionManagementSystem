package com.vivek.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDetail {
	
	private String bankName;		// Bank-Name of Pensioner
	private String accountNumber;	// Account Number of Pensioner
	private String bankType;		// Bank Type of pensioner Eg. Private or Public

}
