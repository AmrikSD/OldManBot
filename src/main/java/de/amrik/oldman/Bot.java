package de.amrik.oldman;

import de.amrik.oldman.ReadPropertyFile;
import java.io.IOException;


public class Bot{
	public static void main(String[] args) throws IOException{
		
		ReadPropertyFile rp = new ReadPropertyFile();
		rp.getProperties();

	}
}
