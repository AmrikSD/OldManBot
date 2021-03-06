package de.amrik.oldman.utils;

import java.util.Date; 

import org.bson.Document;

import net.dv8tion.jda.api.entities.Message;

public class MessageAdapter{

	public static final Document toDocument(Message message){
		
		Date date = new Date();

		return new Document("_id",message.getIdLong())
			.append("GuildID",message.getGuild().getIdLong())
			.append("GuildName",message.getGuild().getName())
			.append("ChannelID",message.getChannel().getIdLong())
			.append("ChannelName",message.getChannel().getName())
			.append("Content",message.getContentRaw())
			.append("AuthorName",message.getAuthor().getName())
			.append("AuthorID",message.getAuthor().getIdLong())
			.append("Time",date);
	}

}
