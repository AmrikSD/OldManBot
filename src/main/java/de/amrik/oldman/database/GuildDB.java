package de.amrik.oldman.database;

import com.mongodb.client.MongoCollection; 

import de.amrik.oldman.ReadPropertyFile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.amrik.oldman.database.MongoDB;


public class GuildDB extends MongoDB {
	
	private static MongoCollection GuildCollection;

	public GuildDB(ReadPropertyFile pf){
		super(pf);
		this.GuildCollection = database.getCollection("discord");

	}

	public MongoCollection getGuildCollection(){
		return GuildCollection;
	}
}
