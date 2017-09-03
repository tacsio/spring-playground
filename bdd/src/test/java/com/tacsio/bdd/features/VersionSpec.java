package com.tacsio.bdd.features;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java8.En;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class VersionSpec implements En {

	@Autowired
	private TestRestTemplate restTemplate;

	private ResponseEntity<String> response;

	public VersionSpec() {
		When("^The client calls /version$", () -> {
			response = restTemplate.getForEntity("/version", String.class);
		});

		Then("^The client receives status code of (\\d+)$", (Integer status) -> {
			assertThat(status, Matchers.is(response.getStatusCode().value()));
		});

		Then("^The client receives server version \"([^\"]*)\"$", (String responseBody) -> {
			assertThat(responseBody, Matchers.equalTo(response.getBody()));
		});

	}
}
