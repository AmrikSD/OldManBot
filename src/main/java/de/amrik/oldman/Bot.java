package de.amrik.oldman;

import de.amrik.oldman.ReadPropertyFile;
import de.amrik.oldman.commands.*;
import de.amrik.oldman.database.GuildDB;
import de.amrik.oldman.database.MessageLogger;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

/** The core of the bot.
  * @author Amrik Singh
  */
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
			jdaBuilder.setActivity(Activity.playing("Runescape"));

			// Connect to the DB so we can store messages and so on
			GuildDB guildDB = new GuildDB(rp);
			MessageLogger msgLogger = new MessageLogger(guildDB);
			jdaBuilder.addEventListeners(msgLogger);

			// Add all the commands the bot can do
			HelpCommand helpCommand = new HelpCommand();
			jdaBuilder.addEventListeners(helpCommand.registerCommand(helpCommand));

			WeatherCommand weatherCommand = new WeatherCommand(rp);
			jdaBuilder.addEventListeners(helpCommand.registerCommand(weatherCommand));

			PingCommand pingCommand = new PingCommand();
			jdaBuilder.addEventListeners(helpCommand.registerCommand(pingCommand));

			// Actually put the bot online
			JDA oldMan = jdaBuilder.build();
			oldMan.awaitReady();

			List<Guild> guilds = oldMan.getGuilds();

			System.out.println("Finished Building The Bot, Connected to " + guilds);


		}catch(LoginException | InterruptedException e){
			// LoginException is what we want to spit out in the case of something going wrong with authentication
			// awaitReady is a blocking meathod so we need InterruptedException
			e.printStackTrace();
		}

	}
}
