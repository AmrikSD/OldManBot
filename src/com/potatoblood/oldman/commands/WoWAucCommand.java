package com.potatoblood.oldman.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class WoWAucCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return Arrays.asList("!wauc");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Returns the auction house information on the specified item";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "World of Warcraft Auction Command";
	}

	@Override
	public List<String> getUsageInstructions() {
		// TODO Auto-generated method stub
		List<String> temps = new ArrayList<String>();
		temps.add("!wauc - [item]");
		temps.add("[item] - The item you wish to find information on in the auction house");
		return temps;
	}

}
