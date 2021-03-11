package com.microservices.namingserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.InetAddress;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NamingServerApplicationTests {

	@Test
	void contextLoads() {
	}

	// Service must be running in background, otherwise unit test will fail
	@Test
	public void testEurekaNamingServer() throws Exception {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		String ipLocalHost = InetAddress.getLocalHost().getHostAddress();
		// Capture GET response
		ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8761", String.class);

		// Confirm Successful Status Code in response
		assertTrue(response.getStatusCode().is2xxSuccessful());
		// Confirm Response contains IP of Local Host
		assertTrue(response.getBody().contains(ipLocalHost));
	}
}