package com.vivek.pms.pensionmanagement.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vivek.pms.pensionmanagement.model.ProcessPensionInput;


@SpringBootTest
public class ProcessPensionInputTest {

	@Test
	void beanCreationTest() {
		assertNotNull(new ProcessPensionInput()) ;
	}
	
	@Test
	void noArgsConstructorTest() {
		ProcessPensionInput ppi = new ProcessPensionInput() ;
		assertNotNull(ppi) ;
	}
	
	@Test 
	void allArgsConstructorTest() {
		ProcessPensionInput ppi = new ProcessPensionInput("4254362356623") ;
		assertNotNull(ppi) ;
	}
	
	@Test
	void settersTest() {
		ProcessPensionInput ppi = new ProcessPensionInput() ;
		ppi.setAadhaarNumber("2432545236576245");
		assertNotNull(ppi) ;
	}
	
	@Test
	void gettersTest() {
		ProcessPensionInput ppi = new ProcessPensionInput("4254362356623") ;
		String aadhaar = ppi.getAadhaarNumber() ;
		assertEquals("4254362356623", aadhaar) ;
	}
	
	
	
}
