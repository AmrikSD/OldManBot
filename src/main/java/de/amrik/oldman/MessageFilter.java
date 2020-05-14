package de.amrik.oldman;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

import de.amrik.oldman.database.WordDB;
import de.amrik.oldman.utils.MessageAdapter;

import com.mongodb.client.MongoCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.bson.Document;

enum Level{
	WARN,
	KICK,
	BAN
}

/** Filters every message that is sent to the server, takes appropriate action based upon the word given the databse
  * @author Amrik Singh
  * @version 0.1.0
  * @since 0.1.0
  */
public class MessageFilter extends ListenerAdapter{

	private WordDB WordDatabase;
	private MongoCollection collection;

	public MessageFilter(WordDB wordDB){
		this.WordDatabase = wordDB;
		this.collection = wordDB.getWordCollection();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		
		Member selfMember = e.getGuild().getSelfMember();
		Member member = e.getGuild().getMember(e.getAuthor());

		//If we can't kick them, leave it alone
		if(!selfMember.canInteract(member)){
			//return;
		}

		ArrayList<Document> badWords = new ArrayList<Document>();

		MongoCursor<Document> cursor = collection.find().iterator();  
		

		try {
			// Get a list of bad words
			while (cursor.hasNext()) {
				badWords.add(cursor.next());
			}

		} finally {
			cursor.close();
			String sentMessage = e.getMessage().getContentRaw();
			String[] arrOfWords = e.getMessage().getContentRaw().split(" ");
			// For each word sent in the message, check it against the list of known bad words
			for(String word: arrOfWords){
				for(Document d: badWords){
					//if there is a match, punish the user appropriately, given in the bad words list.
					if(word.toLowerCase().equals(d.getString("word").toLowerCase())) {
						if(d.getString("punishment").toLowerCase().equals("warn")){
							Level punishLevel = Level.WARN;
							warnUser(e,word,punishLevel);
							return; //Only punish once.
						}
						if(d.getString("punishment").toLowerCase().equals("kick")){
							kickUser(e,word);
							return;
						}
						if(d.getString("punishment").toLowerCase().equals("ban")){
							banUser(e,word);
							return;
						}
		
					}
				}
			}	
		}
	}

	/** Sends a message to the discord channel stating the bots intentions, either to warn, kick, or ban a user for saying a given word. 
	 * @author Amrik Singh
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	private void warnUser(MessageReceivedEvent e, String word, Level lvl){
		
		String punishLevel = "**WARN**";
		switch(lvl) {
			case WARN:
				punishLevel = "**WARN**";
				break;
			case KICK:
				punishLevel = "**KICK**";
				break;
			case BAN:
				punishLevel = "**BAN**";
				break;
		}

		String repl = word.replaceAll("\\B\\w\\B", "*");
		repl = " `(" + repl + ")`";

		e.getChannel().sendMessage(e.getAuthor().getAsMention()+" is being automoderated with level "+punishLevel+repl).queue();
		e.getMessage().delete().queue();

	}
	
	/** @see warnUser
	 *
	 */
	private void kickUser(MessageReceivedEvent e, String word){

		warnUser(e,word, Level.KICK);
		
		e.getGuild().kick(e.getGuild().getMember(e.getAuthor())).queue();

	}
	
	private void banUser(MessageReceivedEvent e, String word){
		warnUser(e,word, Level.BAN);
		e.getGuild().ban(e.getGuild().getMember(e.getAuthor()),0).queue();
	}

}


