package com.potatoblood.oldman.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class WoWAucCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {
		
	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList("!wauc");
	}

	@Override
	public String getDescription() {
		return "Returns the auction house information on the specified item";
	}

	@Override
	public String getName() {
		return "World of Warcraft Auction Command";
	}

	@Override
	public List<String> getUsageInstructions() {
		List<String> temps = new ArrayList<String>();
		temps.add("!wauc - <item>");
		temps.add("<item> - The item you wish to find information on in the auction house");
		return temps;
	}

}
