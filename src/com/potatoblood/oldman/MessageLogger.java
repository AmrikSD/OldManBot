package com.potatoblood.oldman;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageLogger extends ListenerAdapter {

	public void onMessageReceived(MessageReceivedEvent e) {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar calObj = Calendar.getInstance();

		String TIME = ("[" + dateFormat.format(calObj.getTime()) + "]");
		String USER = e.getAuthor().getName();
		String MESSAGE = e.getMessage().getContentDisplay();

		String LogMessage = "";

		if (e.getAuthor().isBot())
			return;

		if (e.isFromType(ChannelType.PRIVATE)) {

			LogMessage = (TIME + " [PM] " + USER + ": " + MESSAGE);
			System.out.println(LogMessage);
		} else {
			String GUILD = ("[" + e.getGuild().getName() + "]");
			String CHANNEL = ("[" + e.getChannel().getName() + "]");

			LogMessage = (TIME + " " + GUILD + " " + CHANNEL + " " + USER + ": " + MESSAGE);
			System.out.println(LogMessage);
		}
		
	}
}
