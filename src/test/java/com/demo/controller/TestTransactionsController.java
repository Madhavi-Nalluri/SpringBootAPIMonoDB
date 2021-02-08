package com.demo.controller;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import java.net.URI;
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
public class TestTransactionsController {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(1525);

	/*
	 * For 200 Status Code
	 */
	@Test
	public void TestCreateStausCode() throws URISyntaxException {

		stubFor(post(urlEqualTo("/transactions/"))
				.withRequestBody(
						equalToJson("{\"accountNumber\": \"123456\", \"amount\": 500.00 , \"type\": \"DEPOSIT\" }"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
						.withBody("inserted transactions successfully")));

		given().body("{\"accountNumber\": \"123456\", \"amount\": 500.00 , \"type\": \"DEPOSIT\" }").when()
				.post(new URI("http://localhost:1525/transactions/")).then().assertThat().statusCode(200);
	}

	/*
	 * For ResponseBody
	 */
	@Test
	public void TestCreateResponseBody() throws URISyntaxException {

		stubFor(post(urlEqualTo("/transactions/"))
				.withRequestBody(
						equalToJson("{\"accountNumber\": \"123456\", \"amount\": 500.00 , \"type\": \"DEPOSIT\" }"))
				.willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200)
						.withBody("inserted transactions successfully")));

		given().body("{\"accountNumber\": \"123456\", \"amount\": 500.00 , \"type\": \"DEPOSIT\" }").when()
				.post(new URI("http://localhost:1525/transactions/")).then().assertThat()
				.body(containsString("inserted transactions successfully"));
	}

	/*
	 * For 200 Status Code
	 */
	@Test
	public void TestGetTransactionsWithDateRangeStatusCode() throws URISyntaxException {

		stubFor(get(urlEqualTo(
				"/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&start=12-01-2020&end=02-05-2021"))
						.willReturn(aResponse().withHeader("Content-Type", "text/plain").withStatus(200).withBody(
								"{ \"id\": \"1abhgvdu\",\"accountNumber\": \"123456\", \"amount\": 500.00 }, \"type\": \"DEPOSIT\" }")));

		given().when().get(new URI(
				"http://localhost:1525/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&start=12-01-2020&end=02-05-2021"))
				.then().assertThat().statusCode(200);
	}

	/*
	 * For ResponseBody
	 */
	@Test
	public void TestGetTransactionsWithDateRangeResponseBody() throws URISyntaxException {

		stubFor(get(urlEqualTo(
				"/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&start=12-01-2020&end=02-05-2021"))
						.willReturn(aResponse().withHeader("Content-Type", "text/plain").withStatus(200).withBody(
								"{ \"id\": \"1abhgvdu\",\"accountNumber\": \"123456\", \"amount\": 500.00 }, \"type\": \"DEPOSIT\" }")));

		given().when().get(new URI(
				"http://localhost:1525/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&start=12-01-2020&end=02-05-2021"))
				.then().assertThat().body(containsString(
						"{ \"id\": \"1abhgvdu\",\"accountNumber\": \"123456\", \"amount\": 500.00 }, \"type\": \"DEPOSIT\" }"));
	}

	/*
	 * For 200 Status Code
	 */
	@Test
	public void TestGetTransactionsWithDateRangeAndTypeStatusCode() throws URISyntaxException {

		stubFor(get(urlEqualTo(
				"/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&type=DEPOSIT&start=12-01-2020&end=02-05-2021"))
						.willReturn(aResponse().withHeader("Content-Type", "text/plain").withStatus(200).withBody(
								"{ \"id\": \"3hjdbndgh\",\"accountNumber\": \"123456\", \"amount\": 500.00 }, \"type\": \"DEPOSIT\" }")));

		given().when().get(new URI(
				"http://localhost:1525/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&type=DEPOSIT&start=12-01-2020&end=02-05-2021"))
				.then().assertThat().statusCode(200);
	}

	/*
	 * For ResponseBody
	 */
	@Test
	public void TestGetTransactionsWithDateRangeAndTypeResponseBody() throws URISyntaxException {

		stubFor(get(urlEqualTo(
				"/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&type=DEPOSIT&start=12-01-2020&end=02-05-2021"))
						.willReturn(aResponse().withHeader("Content-Type", "text/plain").withStatus(200).withBody(
								"{ \"id\": \"3hjdbndgh\",\"accountNumber\": \"123456\", \"amount\": 500.00 }, \"type\": \"DEPOSIT\" }")));

		given().when().get(new URI(
				"http://localhost:1525/transactions/getTransactionsWithDateRange/0/5?accountNumber=123456&type=DEPOSIT&start=12-01-2020&end=02-05-2021"))
				.then().assertThat().body(containsString(
						"{ \"id\": \"3hjdbndgh\",\"accountNumber\": \"123456\", \"amount\": 500.00 }, \"type\": \"DEPOSIT\" }"));
	}
}
