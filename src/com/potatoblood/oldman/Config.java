package com.potatoblood.oldman;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {

	static JSONParser parser = new JSONParser();
	static JSONObject JSONObj = new JSONObject();

	public static JSONObject getDiscordJSON() {

		try {
			JSONObj = (JSONObject) parser.parse(new FileReader("./res/config.JSON"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		JSONObject DiscordJSON = (JSONObject) JSONObj.get("Discord");
		return DiscordJSON;
	}

	public static JSONObject getTwitterJSON() {

		try {
			JSONObj = (JSONObject) parser.parse(new FileReader("./res/config.JSON"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		JSONObject TwitterJSON = (JSONObject) JSONObj.get("Twitter");
		return TwitterJSON;
	}
	
	public static JSONObject getWeatherJSON() {

		try {
			JSONObj = (JSONObject) parser.parse(new FileReader("./res/config.JSON"));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		JSONObject WeatherJSON = (JSONObject) JSONObj.get("Weather");
		return WeatherJSON;
	}

}
