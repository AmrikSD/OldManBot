package de.amrik.oldman.database;

import de.amrik.oldman.ReadPropertyFile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.amrik.oldman.database.MongoDB;


public class GuildDB extends MongoDB {
	public GuildDB(ReadPropertyFile pf){
		super(pf);
	}
}
