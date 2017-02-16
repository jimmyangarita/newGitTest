package com.jdap.auction.service.client.bid;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boolean boolean1 = Boolean.valueOf("true");
		boolean boolean2 = Boolean.parseBoolean("true");

		System.out.println("boolean1: " + boolean1);
		System.out.println("boolean2: " + boolean2);

		Boolean b = true;
		String str = String.valueOf(b);
		System.out.println("1str: " + str);
		str = Boolean.toString(b);
		System.out.println("2str: " + str);

		boolean b1 = true;
		str = String.valueOf(b1);
		System.out.println("3str: " + str);
		str = Boolean.toString(b1);
		System.out.println("4str: " + str);

		JSONParser parser = new JSONParser();
		String s = "{\"UMGID\" : \"0853b5bb-2a5e-47e3-93b4-511e1f625cf8\"}";

		try {

			Object obj = parser.parse(s);
			JSONObject engineJson = (JSONObject) obj;
			String engineID = engineJson.get("UMGID").toString();
			System.out.println("Engine ID: " + engineID);

		} catch (ParseException pe) {
			System.out.println("position: " + pe.getPosition());
			System.out.println(pe);
		}

	}

}
