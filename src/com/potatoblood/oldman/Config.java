package com.potatoblood.oldman;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {

	public static JSONObject getDiscordJSON() {
		JSONParser parser = new JSONParser();

		JSONObject JSONObj = null;
		
		try {
			JSONObj = (JSONObject) parser.parse(new FileReader("./res/config.JSON"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject DiscordJSON = (JSONObject) JSONObj.get("Discord");
		return DiscordJSON;
	}

	public static JSONObject getTwitterJSON() {
		JSONParser parser = new JSONParser();
		
		JSONObject JSONObj = null;
		
		
		try {
			JSONObj = (JSONObject) parser.parse(new FileReader("./res/config.JSON"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject TwitterJSON = (JSONObject) JSONObj.get("Twitter");
		return TwitterJSON;
	}

}
