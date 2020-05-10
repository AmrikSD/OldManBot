package de.amrik.oldman;

import java.util.List;
import java.util.ArrayList;

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
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.bson.Document;

/** Represets logs every message that is sent to the bot.
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

		ArrayList<Document> badWords = new ArrayList<Document>();

		MongoCursor<Document> cursor = collection.find().iterator();  
		

		try {
			while (cursor.hasNext()) {
				badWords.add(cursor.next());
			}

		} finally {
			cursor.close();
			for(Document d: badWords){
				System.out.println(d);
				
			}
		}
	}

}


