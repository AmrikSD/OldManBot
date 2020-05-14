package de.amrik.oldman.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
/**
*/
public class PingCommand extends Command {

    private static final String NO_NAME = "No name provided for this command. Sorry!";
    private static final String NO_DESCRIPTION = "No description has been provided for this command. Sorry!";
    private static final String NO_USAGE = "No usage instructions have been provided for this command. Sorry!";

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args){
	String s = "pong";
	if(args[0].equals("!pong")){
		s="ping";	
	}
	e.getTextChannel().sendMessage(s).queue();
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!ping", "!pong");
    }

    @Override
    public String getDescription(){
        return "Returns ping, or pong :thinking:";
    }

    @Override
    public String getName(){
        return "Ping Command";
    }

    @Override
    public List<String> getUsageInstructions(){

	    return Collections.singletonList(
                "!ping  **OR**  !pong\n"
                + "!ping - returns \"pong\".\n"
                + "!pong - returns \"ping\".\n");
    }

}
