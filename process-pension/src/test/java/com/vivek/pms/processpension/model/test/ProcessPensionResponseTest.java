package com.vivek.pms.processpension.model.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vivek.pms.processpension.model.ProcessPensionResponse;


@SpringBootTest
public class ProcessPensionResponseTest {

	@Test
	void beanCreationTest() {
		assertNotNull(new ProcessPensionResponse());
	}

	@Test
	void noArgsConstructorTest() {
		ProcessPensionResponse processPensionResponse = new ProcessPensionResponse();
		assertThat(assertThat(processPensionResponse).isNotNull());
	}

}
