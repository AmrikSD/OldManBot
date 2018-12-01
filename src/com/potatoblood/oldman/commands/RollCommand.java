package com.potatoblood.oldman.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lib.roll;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RollCommand extends Command {

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {

		if(args.length==1) {
			roll r = new roll();
			sendMessage(e, r.toString());
			return;
		}
		
		if (args.length != 2) {
			sendMessage(e, "Usage: " + getUsageInstructions().toString());
			return;
		}
		try {
			String[] splitString = args[1].toLowerCase().split("d");
			
			int rolls = Integer.parseInt(splitString[0]);
			int sides = Integer.parseInt(splitString[1]);
			
			roll r = new roll(rolls,sides);
			
			sendMessage(e, r.toString());
			
		} catch (Exception er) {
			sendMessage(e, "Something went wrong, message Amrik");
		}

	}

	@Override
	public List<String> getAliases() {
		return Arrays.asList("!roll", "!dice");
	}

	@Override
	public String getDescription() {
		return "Rolls dice in the familiar D&D way!";
	}

	@Override
	public String getName() {
		return "Dice Roller";
	}

	@Override
	public List<String> getUsageInstructions() {
		return Collections.singletonList("!roll   **OR**  !roll *<args>*\n" + "!roll - returns a 1d20 die roll.\n"
				+ "!roll <2d10> - rolls 2 10 sided dice");
	}

}