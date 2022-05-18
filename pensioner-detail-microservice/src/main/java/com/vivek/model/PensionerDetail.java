package com.vivek.model;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PensionerDetail {	
	@Id
	private String aadhaarNumber;						// Aadhaar-Number  - Pensioner
	private String name;								// Name - Pensioner
	private String dateOfBirth;							// DOB - Pensioner
	private String panNumber;							// PAN number - Pensioner
	private double salary;								// Salary - Pensioner
	private double allowance;							// Allowance - Pensioner
	private String pensionType;							// Pension Type - Pensioner
	@Embedded
	private BankDetail bankDetail;


}
