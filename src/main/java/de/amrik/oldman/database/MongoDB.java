package de.amrik.oldman.database;

import de.amrik.oldman.ReadPropertyFile;

import java.util.Iterator;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/** Represets a mongoDB database.
  * @author Amrik Singh
  * @version 0.1.0
  * @since 0.1.0
  */
public abstract class MongoDB {

	private static ReadPropertyFile propertyFile = new ReadPropertyFile();
	private static MongoClientURI mongoClientURI = new MongoClientURI(propertyFile.getMongoClientURI());
	private static MongoClient mongoClient = new MongoClient(mongoClientURI);
	protected static MongoDatabase database = mongoClient.getDatabase(propertyFile.getMongoDatabase());

	static void createCollection(MongoDatabase database, String s){
		Iterator iterator = database.listCollectionNames().iterator();
		while(iterator.hasNext()){
			String str = (String) iterator.next();
			if(str.equalsIgnoreCase(s)){
				return;
			}
		}
		database.createCollection(s);
	}

	static MongoCollection<Document> getCollection(MongoDatabase database, String s) {
		return database.getCollection(s);
	}

	static Document getDocument(MongoCollection<Document> documents, String key, String id) {
		return documents.find(new Document(key, id)).first();
	}

	static Document getDocument(MongoCollection<Document> documents, String key, long id) {
		return documents.find(new Document(key, id)).first();
	}

	static void delete(MongoCollection<Document> documents, Document document) {

		if (document == null){
			return;
		}
		documents.deleteOne(document);
	}

}
