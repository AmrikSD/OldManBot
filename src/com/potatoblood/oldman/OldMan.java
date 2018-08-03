package com.potatoblood.oldman;

import javax.security.auth.login.LoginException;

import com.potatoblood.oldman.commands.HelpCommand;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import twitter.TwitterListener;

/*
 *	This class is used to initialize the bot.
 *	Additionally listens for messages.
 * */
public class OldMan extends ListenerAdapter {

	public static void main(String[] args) {

		setupBot();

	}

	private static void addCommands(JDABuilder jdaBuilder) {
		HelpCommand help = new HelpCommand();
		jdaBuilder.addEventListener(help.registerCommand(help));
	}
	
	
	private static void setupBot() {
		// TODO Auto-generated method stub
		try {
			JDABuilder jdaBuilder = new JDABuilder(AccountType.BOT)
					.setToken("MTgwNDUwNDg1Njk2NzI0OTky.DhXhZg.ZGs1_a5Igc05dR2nTWuL_nhzzWY");

			addCommands(jdaBuilder);

			JDA jda = jdaBuilder.buildBlocking();
			
			TwitterListener.StartListener(jda); //Start the politics auto-chat

		} catch (LoginException | InterruptedException e) {

			e.printStackTrace();

		}
	}
}
