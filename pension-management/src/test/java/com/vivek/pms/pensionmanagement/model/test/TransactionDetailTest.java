package com.vivek.pms.pensionmanagement.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vivek.pms.pensionmanagement.model.TransactionDetail;


@SpringBootTest
public class TransactionDetailTest {
	
	@Test
	void beanCreationTest() {
		assertNotNull(new TransactionDetail()) ;
	}
	
	@Test
	void noArgsConstructorTest() {
		TransactionDetail td = new TransactionDetail() ;
		assertNotNull(td) ;
	}
	
	@Test 
	void allArgsConstructorTest() {
		TransactionDetail td = new TransactionDetail("4254362356623", 123.32, "12345678", "22-May-2022") ;
		assertNotNull(td) ;
	}
	
	@Test
	void settersTest() {
		TransactionDetail td = new TransactionDetail() ;
		td.setAadhaarNumber("2432545236576245");
		td.setAccountNumber("12345678");
		td.setTransactionAmount(12345.22);
		td.setTransactionTimestamp("22-MAY-2022");
		assertNotNull(td) ;
	}
	
	@Test
	void gettersTest() {
		TransactionDetail td = new TransactionDetail("4254362356623", 123.32, "12345678", "22-May-2022") ;
		String aadhaar = td.getAadhaarNumber() ;
		assertEquals("4254362356623", aadhaar) ;
		String account = td.getAccountNumber() ;
		assertEquals("12345678", account) ;
	}

}
