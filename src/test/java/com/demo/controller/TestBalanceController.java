package com.demo.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.net.URISyntaxException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestBalanceController {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(1525);

	public void setupStub() {

		stubFor(get(urlEqualTo("/balance/getBalanceByAccountNumber?accountNumber=123456"))
				.willReturn(aResponse().withHeader("Content-Type", "text/plain").withStatus(200)
						.withBody("{ \"id\": \"1abhgvdu\",\"accountNumber\": \"123456\", \"balance\": 500.00 }")));
	}

	/*
	 * For ResponseBody
	 */
	@Test
	public void TestGetBalanceByAccountNumberStatusCode() throws URISyntaxException {

		setupStub();

		given().when().get("http://localhost:1525/balance/getBalanceByAccountNumber?accountNumber=123456").then()
				.assertThat().statusCode(200);
	}

	/*
	 * For Response Body
	 */
	@Test
	public void TestGetBalanceByAccountNumberWithResponse() throws URISyntaxException {

		setupStub();

		given().when().get("http://localhost:1525/balance/getBalanceByAccountNumber?accountNumber=123456").then()
				.assertThat()
				.body(containsString("{ \"id\": \"1abhgvdu\",\"accountNumber\": \"123456\", \"balance\": 500.00 }"));
	}
}
