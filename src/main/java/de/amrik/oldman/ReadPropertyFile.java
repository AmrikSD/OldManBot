package de.amrik.oldman;

import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.net.URL;

public class ReadPropertyFile{

	public static final String CONFIG_FILE = "config.properties";
	private String DiscordToken;

	private String TwitterAPIKey;
	private String TwitterAPISecret;
	private String TwitterToken;
	private String TwitterTokenSecret;

	private String WeatherAPIKey;

	private String MongoDatabase;
	private String MongoClientURI;
	private int MongoClientPort;

	InputStream inputStream;
	Properties prop = new Properties();

	public void getProperties() throws java.io.IOException {
		try{
			inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + CONFIG_FILE + "' not found in the classpath");
			}

			prop.load(inputStream);

			setDiscordToken(prop.getProperty("DiscordToken"));
			setTwitterAPIKey(prop.getProperty("TwitterAPIKey"));
			setTwitterAPISecret(prop.getProperty("TwitterAPISecret"));
			setTwitterToken(prop.getProperty("TwitterToken"));
			setTwitterTokenSecret(prop.getProperty("TwitterTokenSecret"));
			setWeatherAPIKey(prop.getProperty("WeatherAPIKey"));
			setMongoDatabase(prop.getProperty("MongoDatabase"));
			setMongoClientURI(prop.getProperty("MongoClientURI"));
			setMongoClientPort(prop.getProperty("MongoClientPort"));

		}catch(Exception e){
			System.out.println("Exception: "+e);
		}
		inputStream.close();
	}

	public void setMongoDatabase(String MongoDatabase){
		this.MongoDatabase = MongoDatabase;
	}

	public void setMongoClientURI(String MongoClientURI){
		this.MongoClientURI = MongoClientURI;
	}

	public void setDiscordToken(String DiscordToken){
		this.DiscordToken = DiscordToken;
	}

	public void setTwitterAPIKey(String TwitterAPIKey){
		this.TwitterAPIKey = TwitterAPIKey;
	}

	public void setTwitterAPISecret(String TwitterAPISecret){
		this.TwitterAPISecret = TwitterAPISecret;
	}

	public void setTwitterToken(String TwitterToken){
		this.TwitterToken = TwitterToken;
	}

	public void setTwitterTokenSecret(String TwitterTokenSecret){
		this.TwitterTokenSecret = TwitterTokenSecret;
	}

	public void setWeatherAPIKey(String WeatherAPIKey){
		this.WeatherAPIKey = WeatherAPIKey;
	}

	// We need to convert from a string to int here
	public void setMongoClientPort(String MongoClientPort){
		this.MongoClientPort = Integer.parseInt(MongoClientPort);
	}

	public String getDiscordToken(){
		return DiscordToken;
	}

	public String getTwitterAPIKey(){
		return TwitterAPIKey;
	}

	public String getTwitterAPISecret(){
		return TwitterAPISecret;
	}

	public String getTwitterToken(){
		return TwitterToken;
	}

	public String getTwitterTokenSecret(){
		return TwitterTokenSecret;
	}

	public String getWeatherAPIKey(){
		return WeatherAPIKey;
	}
	public String getMongoDatabase(){
		return MongoDatabase;
	}

	public String getMongoClientURI(){
		return MongoClientURI;
	}

	public int getMongoClientPort(){
		return MongoClientPort;
	}


}
