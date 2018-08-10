package com.potatoblood.oldman.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PingCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {
		if (args[0].equals("!ping"))
			sendMessage(e, "Pong!");
		else if (args[0].equals("!pong"))
			sendMessage(e, "Ping!");
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return Arrays.asList("!ping", "!pong");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Returns the opposite of the users input (!ping/Pong!)";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "!ping Command";
	}

	@Override
	public List<String> getUsageInstructions() {
		// TODO Auto-generated method stub
		List<String> temps = new ArrayList<String>();
		temps.add("Use either !ping or !pong to get a reply, with the opposite to the input.");
		return temps;
	}

}
