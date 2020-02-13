package de.amrik.oldman.utils;


import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import net.dv8tion.jda.api.entities.Guild;

public class GuildAdaptor {

	public static final DBObject toDBObject(Guild guild){
		return new BasicDBObject();
	}

}
