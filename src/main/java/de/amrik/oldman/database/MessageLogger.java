package de.amrik.oldman.database;

import java.util.List;

import de.amrik.oldman.database.GuildDB;
import de.amrik.oldman.utils.MessageAdapter;

import com.mongodb.client.MongoCollection;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import org.bson.Document;

/** Logs every message that is sent to the bot to a {@link net.dv8tion.jda.api.entities.Channel channel} to a {@link de.amrik.oldman.database.GuildDB GuildDB}
  * @author Amrik Singh
  * @version 0.1.0
  * @since 0.1.0
  */
public class MessageLogger extends ListenerAdapter{

	private GuildDB GuildDatabase;
	private MongoCollection collection;

	public MessageLogger(GuildDB guildDB){
		this.GuildDatabase = guildDB;
		this.collection = guildDB.getGuildCollection();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {

		collection.insertOne(MessageAdapter.toDocument(e.getMessage()));

	}

}


