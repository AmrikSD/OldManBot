package de.amrik.oldman.commands;

import de.amrik.oldman.ReadPropertyFile;

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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


public class WeatherCommand extends Command {

	final String celsius = "\u2103";
	final String fahrenheit = "\u2109";
	final String kelvin = "\u212a";

	private String APIKey;
	private String defaultCity;
	private String units;

	private String APIBase;

	private boolean weatherIsEnabled = true;

	public WeatherCommand(ReadPropertyFile rp){
		this.APIKey = rp.getWeatherAPIKey();
		this.defaultCity = "Leicester";
		this.units = "metric";
		
		APIBase = "https://api.openweathermap.org/data/2.5/weather?" + "units=" + units + "&appid=" + APIKey + "&q=";
	}

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {
		e.getChannel().sendTyping().queue();

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
					e.getTextChannel().sendMessage("City not found").queue();
					return;
				}
				MessageEmbed ME = buildMessage(weatherData);
				e.getTextChannel().sendMessage(ME).queue();

			} catch (UnirestException e1) {
				sendMessage(e,
						"something went wrong with the weather, ask me later or go look outside. If I'm still this cranky ask the person who should be taking care of me to sort me out.");
				e1.printStackTrace();
			}
		}

	}

	private MessageEmbed buildMessage(org.json.JSONObject weatherData) {
		// An Example of each field -
		// https://cdn.discordapp.com/attachments/475468825513558023/476494812585918464/Zc3qwqB.png
		String OLD_MAN_PIC = "https://i.imgur.com/UaVO49N.png";

		EmbedBuilder builder = new EmbedBuilder();
		builder.setColor(Color.blue); // Old Man has a blue party hat cmon now

		// Variables for the "Author" Field
		String name = weatherData.getString("name");
		int id = weatherData.getInt("id");
		String country = weatherData.getJSONObject("sys").get("country").toString().toUpperCase();



		builder.setAuthor(name + ", " + country, "https://openweathermap.org/city/" + id,
				"http://openweathermap.org/images/flags/" + country.toLowerCase() + ".png");

		// Variables for the "Title" Field
		long dt = weatherData.getLong("dt");
		Date date = new Date(dt * 1000L);
		String dayAndTime = new DateFormatManager("EEEE hh:mm a").format(date);

		builder.setTitle(dayAndTime);

		// Variables for the "Description" field.
		org.json.JSONArray weather = weatherData.getJSONArray("weather");
		org.json.JSONObject[] weatherIndex = new org.json.JSONObject[weather.length()];
		for (int i = 0; i < weather.length(); i++) {
			weatherIndex[i] = (org.json.JSONObject) weather.get(i);
		}
		String description = weatherIndex[0].get("description").toString().toUpperCase();
		description = description.substring(0, 1).toUpperCase() + description.substring(1).toLowerCase();

		builder.setDescription(description);

		// Variables for the "Field" field(s) - this is done dynamically so the ordering
		// isn't ideal
		org.json.JSONObject main = weatherData.getJSONObject("main");
		Iterator<?> keys = main.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String BeautifiedKey = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase().replace('_', ' ');
			builder.addField(BeautifiedKey, main.get(key).toString(), true);

		}

		// Variables for the "Thumbnail" field
		String baseWeatherIcon = "https://raw.githubusercontent.com/AmrikSD/OldManBot/84e469c549ea0c945515684c85687db787c049d9/res/weather/";
		String currWeatherIcon = weatherIndex[0].getString("icon").toString();
		String dotPNG = ".png";
		builder.setThumbnail(baseWeatherIcon + currWeatherIcon + dotPNG);

		// Shameless Plug
		builder.setFooter("https://github.com/AmrikSD/OldManBot", OLD_MAN_PIC);
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
		return Collections.singletonList("!Weather   **OR**  !We *<city>*\n"
				+ "!Weather - returns the weather of the default city in the config file.\n"
				+ "!weather <city> - Returns the weather of the city.\n" + "__Example:__ !we London, UK");
	}

} 
