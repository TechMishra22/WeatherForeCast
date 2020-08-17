package com.faire.lab;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import com.faire.lab.controller.WeatherReportController;


@RunWith(SpringRunner.class)
@SpringBootTest
class WeatherForecast8ApplicationTests {

	private static final String URI = "http://localhost:8080";
	
	@Test
	public void testApiCall() throws JSONException, ParseException {
		Client client = ClientBuilder.newClient();
		javax.ws.rs.core.Response response = client
                .target(URI + "/weather-forecast/pune/report-type/1")
                .request().header("Content-type", MediaType.APPLICATION_JSON)
                .get();
        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

		
		  WeatherReportController weatherReportController =
		  response.readEntity(WeatherReportController.class);
		  Assert.assertTrue("At least one list is present",weatherReportController.
		  getResult("pune",(long) 1).size() > 0);
		 

	}
	@Test
    public void testNameAndTimeApi() throws JSONException, ParseException {
        Client client = ClientBuilder.newClient();

        Response response = client
                .target(URI + "/weather-forecast/pune/report-type/1")
                .request().header("Content-type", MediaType.APPLICATION_JSON)
                .get();
        assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        WeatherReportController weatherReportController = response.readEntity(WeatherReportController.class);

        Assert.assertTrue("At least one list is present",weatherReportController.getResult("pune",(long) 1).size() > 0);
    }

}
