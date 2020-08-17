package com.faire.lab.dto;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {
	public static void main(String[] args) {
		String res = "{\r\n" + "   \"cod\":\"200\",\r\n" + "   \"message\":0,\r\n" + "   \"cnt\":40,\r\n"
				+ "   \"list\":[\r\n" + "      {\r\n" + "         \"dt\":1597590000,\r\n" + "         \"main\":{\r\n"
				+ "            \"temp\":300.79,\r\n" + "            \"feels_like\":300.37,\r\n"
				+ "            \"temp_min\":300.6,\r\n" + "            \"temp_max\":300.79,\r\n"
				+ "            \"pressure\":1003,\r\n" + "            \"sea_level\":1003,\r\n"
				+ "            \"grnd_level\":1002,\r\n" + "            \"humidity\":84,\r\n"
				+ "            \"temp_kf\":0.19\r\n" + "         },\r\n" + "         \"weather\":[\r\n"
				+ "            {\r\n" + "               \"id\":501,\r\n" + "               \"main\":\"Rain\",\r\n"
				+ "               \"description\":\"moderate rain\",\r\n" + "               \"icon\":\"10n\"\r\n"
				+ "            }\r\n" + "         ],\r\n" + "         \"clouds\":{\r\n" + "            \"all\":99\r\n"
				+ "         },\r\n" + "         \"wind\":{\r\n" + "            \"speed\":9.5,\r\n"
				+ "            \"deg\":258\r\n" + "         },\r\n" + "         \"visibility\":4847,\r\n"
				+ "         \"pop\":1,\r\n" + "         \"rain\":{\r\n" + "            \"3h\":5.68\r\n"
				+ "         },\r\n" + "         \"sys\":{\r\n" + "            \"pod\":\"n\"\r\n" + "         },\r\n"
				+ "         \"dt_txt\":\"2020-08-16 15:00:00\"\r\n" + "      },\r\n" + "      {\r\n"
				+ "         \"dt\":1597600800,\r\n" + "         \"main\":{\r\n" + "            \"temp\":300.88,\r\n"
				+ "            \"feels_like\":300.29,\r\n" + "            \"temp_min\":300.86,\r\n"
				+ "            \"temp_max\":300.88,\r\n" + "            \"pressure\":1004,\r\n"
				+ "            \"sea_level\":1004,\r\n" + "            \"grnd_level\":1002,\r\n"
				+ "            \"humidity\":84,\r\n" + "            \"temp_kf\":0.02\r\n" + "         },\r\n"
				+ "         \"weather\":[\r\n" + "            {\r\n" + "               \"id\":501,\r\n"
				+ "               \"main\":\"Rain\",\r\n" + "               \"description\":\"moderate rain\",\r\n"
				+ "               \"icon\":\"10n\"\r\n" + "            }\r\n" + "         ],\r\n"
				+ "         \"clouds\":{\r\n" + "            \"all\":100\r\n" + "         },\r\n"
				+ "         \"wind\":{\r\n" + "            \"speed\":9.81,\r\n" + "            \"deg\":253\r\n"
				+ "         },\r\n" + "         \"visibility\":10000,\r\n" + "         \"pop\":1,\r\n"
				+ "         \"rain\":{\r\n" + "            \"3h\":6.93\r\n" + "         },\r\n"
				+ "         \"sys\":{\r\n" + "            \"pod\":\"n\"\r\n" + "         },\r\n"
				+ "         \"dt_txt\":\"2020-08-16 18:00:00\"\r\n" + "      }\r\n" + "  ],\r\n" + "   \"city\":{\r\n"
				+ "      \"id\":1275339,\r\n" + "      \"name\":\"Mumbai\",\r\n" + "      \"coord\":{\r\n"
				+ "         \"lat\":19.0144,\r\n" + "         \"lon\":72.8479\r\n" + "      },\r\n"
				+ "      \"country\":\"IN\",\r\n" + "      \"population\":1000000,\r\n"
				+ "      \"timezone\":19800,\r\n" + "      \"sunrise\":1597539010,\r\n"
				+ "      \"sunset\":1597584950\r\n" + "   }\r\n" + "}";

		JSONArray list = new JSONObject(res).getJSONArray("list");
		for (int i = 0; i < list.length(); i++) {
			JSONObject obj = list.getJSONObject(i).getJSONObject("main");
			System.out.println("dt_txt -: " + list.getJSONObject(i).getString("dt_txt") + "\ntemp_min - : "
					+ obj.getDouble("temp_min") + ",\ntemp_max -: " + obj.getDouble("temp_max") + ",\nhumidity -: "
					+ obj.getLong("humidity") + "\n-------------------------\n");
		}
	}
}