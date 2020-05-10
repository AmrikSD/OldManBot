package de.amrik.oldman.database;

import com.mongodb.client.MongoCollection; 

import de.amrik.oldman.ReadPropertyFile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.amrik.oldman.database.MongoDB;


public class WordDB extends MongoDB {
	
	private static MongoCollection WordCollection;

	public WordDB(ReadPropertyFile pf){
		super(pf);
		this.WordCollection = database.getCollection("words");

	}

	public MongoCollection getWordCollection(){
		return WordCollection;
	}
}
