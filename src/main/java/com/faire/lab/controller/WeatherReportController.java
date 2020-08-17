package com.faire.lab.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.faire.lab.dto.Response;

@RestController
@RequestMapping("/weather-forecast")
public class WeatherReportController {
	private static final Long WORK_START_TIME = 10L;
	private static final Long WORK_END_TIME = 19L;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{city}/report-type/{reportType}")
	public List<Response> getResult(@PathVariable String city, @PathVariable Long reportType)
			throws JSONException, ParseException {
		List<Response> responses = null;
		final String uri = "http://api.openweathermap.org/data/2.5/forecast?q=" + city
				+ "&appid=7da2a0b68395c618ef1310f00e68ded3";
		String result = restTemplate.getForObject(uri, String.class);

		JSONObject root = new JSONObject(result);
		JSONArray list = root.getJSONArray("list");
		JSONObject obj = null;
		int daysCount = 0;
		Date date = null;
		Date lastDate = null;
		if (list.length() > 0) {
			responses = new LinkedList<Response>();
			Response response = null;
			for (int i = 0; i < list.length(); i++) {
				date = SDF.parse(list.getJSONObject(i).getString("dt_txt"));
				obj = list.getJSONObject(i).getJSONObject("main");
				response = new Response();
				response.setCity(city.toUpperCase());
				response.setDate(list.getJSONObject(i).getString("dt_txt"));
				response.setReport_type(
						reportType == 1 ? "Working Hours" : (reportType == 2 ? "Non Working Hours" : "Undefined"));
				response.setTemp_min(obj.getDouble("temp_min"));
				response.setTemp_max(obj.getDouble("temp_max"));
				response.setHumidity(obj.getLong("humidity"));
				if (lastDate == null
						|| lastDate.compareTo(SDF_DATE.parse(list.getJSONObject(i).getString("dt_txt"))) != 0) {
					daysCount += 1;
					lastDate = SDF_DATE.parse(list.getJSONObject(i).getString("dt_txt"));
					if (daysCount > 3)
						break;
				}
				if (daysCount <= 3
						&& (reportType == 1 && date.getHours() >= WORK_START_TIME && date.getHours() <= WORK_END_TIME)
						|| (reportType == 2
								&& !(date.getHours() >= WORK_START_TIME && date.getHours() <= WORK_END_TIME)))
					responses.add(response);
			}
		}
		return responses;
	}
}