package de.amrik.oldman.commands;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.google.gson.*;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
/**
*/
public class WeatherCommand extends Command {
	
    // http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID={APIKEY} 

    //http://api.openweathermap.org/data/2.5/weather?q=konstanz&appid=2256748707e594385bad2351e0aaf799

    private static final String API_CALL = " http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=";

    public String getWeather(String city){
	    System.out.println(city);
	    
	    return "ee";
    }

    @Override
    public void onCommand(MessageReceivedEvent e, String[] args){
	    
	    String city = "";
	    
	    // Get the city the user wants the weather for
	    if(args.length < 1){
		    //TODO: guess the location the user wants.
		    city = "no city given"; 
	    }else{
		    // Join up the rest of the argument as one string to pass to the weather api
		    for(int i = 1; i<args.length; i++){
			    city = city + args[i];
		    }
	    }

	    // Ask the weather api for the city
	    String weather = getWeather(city);

	    // Make the output pretty for the user 
	    e.getTextChannel().sendMessage("it's raining :)").queue();
    }

    @Override
    public List<String> getAliases(){
        return Arrays.asList("!weather", "!we");
    }

    @Override
    public String getDescription(){
        return "Returns information about the weather";
    }

    @Override
    public String getName(){
        return "Weather Command";
    }

    @Override
    public List<String> getUsageInstructions(){

	    return Collections.singletonList(
                "!weather  **OR**  !we\n"
                + "!we - returns the weather in your current (my guess) location.\n"
                + "!weather <location> - returns the weather in the given location.\n");
    }

}
