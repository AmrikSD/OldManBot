package com.potatoblood.oldman.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RollCommand extends Command {

	// https://rolz.org/help/api
	String ApiURLStart = "https://rolz.org/api/?";
	String ApiURLEnd = ".json";
	String APICALLERROR = "Something went wrong rolling your dice, please try again, if it persists moan at Amrik :)";

	@Override
	public void onCommand(MessageReceivedEvent e, String[] args) {

		String userAsMention = e.getAuthor().getAsMention();

		String mergedString = "1d20"; // default to rolling a d20 if nothing else is given

		if (args.length == 2) {
			mergedString = args[1];
		}

		if (args.length > 2) {
			mergedString = "";
			for (int i = 1; i < args.length; i++) {
				mergedString = mergedString.concat(args[i]);
			}
		}

		String result;

		e.getChannel().sendTyping().queue(); // Just in case the user knows the bot was at least trying

		try {
			JSONObject diceData = Unirest.get(ApiURLStart + mergedString + ApiURLEnd).asJson().getBody().getObject();

			result = diceData.get("result").toString();
			String details = (String) diceData.get("details");

			String formattedMessage = (userAsMention + " rolling " + mergedString + ": " + "*" + result + "*"
					+ "```java\n" + details + "\n```");
			sendMessage(e, formattedMessage);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			sendMessage(e, APICALLERROR);
			e1.printStackTrace();
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
				+ "!roll <args> - rolls some dice, using starndard dice notation (yes, that's a thing).\n"
				+ "__Example:__ !roll 1d20+2" + "See https://rolz.org/help/api for specifics on how to roll dice.");
	}

}