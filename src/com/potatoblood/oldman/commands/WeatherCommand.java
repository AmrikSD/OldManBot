package com.potatoblood.oldman.commands;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.lf5.util.DateFormatManager;
import org.json.simple.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.potatoblood.oldman.Config;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class WeatherCommand extends Command {

	final String celsius = "\u2103";
	final String fahrenheit = "\u2109";
	final String kelvin = "\u212a";

	static JSONObject weatherJSON = Config.getWeatherJSON();
	Boolean weatherIsEnabled = (Boolean) weatherJSON.get("Enabled");
	private String APIKey = (String) weatherJSON.get("API Key");
	private String defaultCity = (String) weatherJSON.get("Default City");
	private String units = (String) weatherJSON.get("Units");

	String APIBase = "https://api.openweathermap.org/data/2.5/weather?" + "units=" + units + "&appid=" + APIKey + "&q=";

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {

		
		if (!weatherIsEnabled) {
			sendMessage(e, "Sorry, weather functionality isn't currently enabled!");
			return;
		}

		String city = defaultCity;

		if (args.length > 2) {
			city = args[1];
			for (int i = 2; i < args.length; i++) {
				city = city.concat("%20" + args[i]);
			}
		}

		if (args.length == 2) {
			city = args[1];
		}

		if (weatherIsEnabled) {
			org.json.JSONObject weatherData;
			try {
				weatherData = getWeather(city);

				if (404 == getCod(weatherData)) {
					sendMessage(e, getMessage(weatherData)); // "city not found"
					return;
				}
				MessageEmbed ME = buildMessage(weatherData);
				sendMessage(e, ME);

			} catch (UnirestException e1) {
				sendMessage(e,
						"something went wrong with the weather, ask me later or go look outside. If I'm still this cranky ask the person who should be taking care of me to sort me out.");
				e1.printStackTrace();
			}
		}

	}

	private MessageEmbed buildMessage(org.json.JSONObject weatherData) {
		//An Example of each field - https://cdn.discordapp.com/attachments/475468825513558023/476494812585918464/Zc3qwqB.png
		
		String OLD_MAN_PIC = "https://i.imgur.com/UaVO49N.png";
		
		
		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.blue); //Old Man has a blue party hat cmon now
		
		//Variables for the "Author" Field
		String name = weatherData.getString("name");
		int id = weatherData.getInt("id");
		String country = weatherData.getJSONObject("sys").get("country").toString().toUpperCase();
		
		builder.setAuthor(name + ", " + country, "https://openweathermap.org/city/" + id,
				"http://openweathermap.org/images/flags/" + country + ".png");

		//Variables for the "Title" Field
		long dt = weatherData.getLong("dt");
		Date date = new Date(dt * 1000L);
		String dayAndTime = new DateFormatManager("EEEE hh:mm a").format(date);
		
		builder.setTitle(dayAndTime);

		
		//////
		
		//Variables for the "Description" field.
		org.json.JSONArray weather = weatherData.getJSONArray("weather");
		org.json.JSONObject[] weatherIndex = new org.json.JSONObject[weather.length()];
		for (int i = 0; i < weather.length(); i++) {
			weatherIndex[i] = (org.json.JSONObject) weather.get(i);
		}
		
		String description = weatherIndex[0].get("description").toString().toUpperCase();
		description = description.substring(0, 1).toUpperCase() + description.substring(1).toLowerCase();
		
		builder.setDescription(description);

		//Variables for the "Field" field(s) - this is done dynamically so the ordering isn't ideal
		org.json.JSONObject main = weatherData.getJSONObject("main");
		Iterator<?> keys = main.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String BeautifiedKey = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase().replace('_', ' '); 
			builder.addField(BeautifiedKey, main.get(key).toString(), true);

		}
		
		//Variables for the "Image" field
		String baseWeatherIcon = "http://openweathermap.org/img/w/";
		String currWeatherIcon = weatherIndex[0].getString("icon").toString();
		String dotPNG = ".png";
		builder.setImage(baseWeatherIcon + currWeatherIcon + dotPNG);

		builder.setFooter("https://github.com/AmrikSD/OldManBot",
				OLD_MAN_PIC);

		builder.setThumbnail(OLD_MAN_PIC);

		return builder.build();

	}

	private int getCod(org.json.JSONObject weatherData) {
		return weatherData.getInt("cod");
	}

	private String getMessage(org.json.JSONObject weatherData) {
		return weatherData.getString("message");
	}

	public org.json.JSONObject getWeather(String city) throws UnirestException {
		return Unirest.get(APIBase + city).asJson().getBody().getObject();
	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList("!weather", "!we");
	}

	@Override
	public String getDescription() {
		return "Gets the current weather!";
	}

	@Override
	public String getName() {
		return "Weather Forcast";
	}

	@Override
	public List<String> getUsageInstructions() {
		return Collections.singletonList("!Weather   **OR**  !We *<city>*\n" + "!Weather - returns the weather of the default city in the config file.\n"
				+ "!weather <city> - Returns the weather of the city.\n"
				+ "__Example:__ !we London, UK");
	}

}