package de.amrik.oldman;

import de.amrik.oldman.ReadPropertyFile;

import javax.security.auth.login.LoginException;
import java.io.IOException;


import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Bot extends ListenerAdapter{



	public static void main(String[] args) throws IOException{

		// Read the properties file (contains all the keys for the  bot, discord, twitter, etc)
		ReadPropertyFile rp = new ReadPropertyFile();
		rp.getProperties();

		// Write all of the tokens
		String DiscordToken		= rp.getDiscordToken();
		String TwitterAPIKey		= rp.getTwitterAPIKey();
		String TwitterAPISecret		= rp.getTwitterAPISecret();
		String TwitterToken		= rp.getTwitterToken();
		String TwitterTokenSecret	= rp.getTwitterTokenSecret();
		String WeatherAPIKey		= rp.getWeatherAPIKey();

		// Actually make the bot, and add the commands to it.
		try{
			JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(DiscordToken);

			//TODO: Add the commands

			// Actually put the bot online
			JDA jda = jdaBuilder.build();
			jda.awaitReady();

			System.out.println("Finished Building The Bot");


		}catch(LoginException | InterruptedException e){
			// LoginException is what we want to spit out in the case of something going wrong with authentication
			// awaitReady is a blocking meathod so we need InterruptedException
			e.printStackTrace();
		}

	}
}
