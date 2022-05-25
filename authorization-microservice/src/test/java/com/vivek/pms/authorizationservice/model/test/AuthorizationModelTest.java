package com.vivek.pms.authorizationservice.model.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vivek.pms.authorizationservice.model.AuthRequest;

@SpringBootTest
public class AuthorizationModelTest {
	
	@Test
	void testNoArgsAuthRequestTest() {
		assertThat(new AuthRequest()).isNotNull();
	}

	@Test
	void testAllArgsAuthRequestTest() {
		AuthRequest authRequest = new AuthRequest("Admin", "admin");
		assertNotNull(authRequest) ;
	}

	@Test
	void testSetterAuthRequest() {
		AuthRequest auth = new AuthRequest();
		auth.setUserName("Admin");
		auth.setPassword("admin");
		assertThat(assertThat(auth).isNotNull());

	}
	
	@Test
	void SetterArgsAuthRequestTest() {
		AuthRequest auth = new AuthRequest("Admin", "admin");
		assertEquals("Admin", auth.getUserName());
		assertEquals("admin", auth.getPassword());
	}
}
