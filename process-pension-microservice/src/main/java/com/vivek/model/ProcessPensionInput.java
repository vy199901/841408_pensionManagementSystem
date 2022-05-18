package com.vivek.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessPensionInput {

	private String aadhaarNumber;
	private double pensionAmount;
	private double bankCharge;

}
