package com.tacsio.bdd.features;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java8.Pt;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class HelloSpec implements Pt {

	@Autowired
	private TestRestTemplate restTemplate;
	
	private ResponseEntity<String> response;
	
	private String usuario;
	
	public HelloSpec() {

		Dado("^que \"([^\"]*)\" irá interagir$", (String usuario) -> {
			this.usuario = usuario;
		});

		Quando("^eu faço a requisição$", () -> {
			response = restTemplate.getForEntity("/hello/{user}", String.class, usuario);
		});

		Então("^eu deveria receber um HTTP status (\\d+)$", (Integer statusCode) -> {
			assertThat(statusCode, Matchers.is(response.getStatusCode().value()));
		});

		Então("^a resposta deveria ter a mensagem \"([^\"]*)\"$", (String responseBody) -> {
			assertThat(responseBody, Matchers.equalTo(response.getBody()));
		});

		Dado("^que quem interage é o \"([^\"]*)\"$", (String bb8) -> {
			this.usuario = bb8;
		});

		Quando("^eu faço uma requisição$", () -> {
			response = restTemplate.getForEntity("/hello/{user}", String.class, usuario);
		});

		Então("^eu deveria receber a mensagem \"([^\"]*)\"$", (String response) -> {
			assertThat(response, Matchers.equalTo(this.response.getBody()));
		});
	}
}
