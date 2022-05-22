package com.vivek.pms.pensionerdetail.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PensionerDetail {
	@Id
	private String aadhaarNumber;                   // AadhaarNumber of Pensioner
	private String name;                            // Name of Pensioner
	private String dateOfBirth;                     // DateOfBirth of Pensioner
	private String pan;                             // PAN Number of Pensioner
	private double salaryEarned;					// SalaryEarned by Pensioner
	private double allowances;						// Allowances - Pensioner
	private String pensionType;                     // PensionType - Self or Family 
	@Embedded
	private BankDetail bankDetail;                  // Bank Detail of Pensioner

}
