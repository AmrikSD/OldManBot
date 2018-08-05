package com.potatoblood.oldman;

import javax.security.auth.login.LoginException;

import org.json.simple.JSONObject;

import com.potatoblood.oldman.commands.HelpCommand;
import com.potatoblood.oldman.commands.RollCommand;
import com.potatoblood.oldman.twitter.TwitterListener;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/*
 *	This class is used to initialize the bot.
 *	Additionally listens for messages.
 * */
public class OldMan extends ListenerAdapter {

	public static void main(String[] args) {
		
		setupBot();		
		
	}

	private static void addCommands(JDABuilder jdaBuilder) {
		
		MessageLogger logger = new MessageLogger();		
		jdaBuilder.addEventListener(logger);
		
		
		HelpCommand help = new HelpCommand();
		jdaBuilder.addEventListener(help.registerCommand(help));
		
		RollCommand roll = new RollCommand();
		jdaBuilder.addEventListener(help.registerCommand(roll));
		
		
		
	}

	private static void setupBot() {
		// TODO Auto-generated method stub
		try {
			JSONObject DiscordJSON =  Config.getDiscordJSON();
			String DiscordToken =  DiscordJSON.get("Token").toString();
		
			JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT).setToken(DiscordToken);

			addCommands(jdaBuilder);

			JDA jda = jdaBuilder.buildBlocking();

			TwitterListener.StartListener(jda); // Start the rip from twitter to discord

		} catch (LoginException | InterruptedException e) {

			e.printStackTrace();

		}
	}
}
