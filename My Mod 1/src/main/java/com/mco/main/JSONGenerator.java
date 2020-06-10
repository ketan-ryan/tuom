package com.mco.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.icu.impl.Assert;

import net.minecraft.item.Item;
import scala.util.parsing.json.JSON;

/**
 * @author TheMCO
 * Why spend 1 hour doing something when you could spend 5 automating it?
 */
public class JSONGenerator {

	private static OutputStreamWriter newJson = null;
	private static final ArrayList<Item> BLACKLIST = new ArrayList<Item>() {
		{
			add(TUOMItems.TOPAZ_BOW);
			add(TUOMItems.DARK_STAFF);
		}
	};

	public static void generateJson(ArrayList<Item> items) throws IOException
	{
		for(Item item : items) 
		{
			File file = new File(item.getUnlocalizedName().substring(5) + ".json");
			
			if(!BLACKLIST.contains(item) && !file.exists())
				createFile(item);
		}
	}
	
	private static void createFile(Item item) throws FileNotFoundException
	{
		try {
			newJson = new FileWriter("C:/Users//kyure/git/tuom/My Mod 1/src/main/resources/assets/tuom/models/item/" + item.getUnlocalizedName().substring(5) + ".json");			
			newJson.write("{\n");
			newJson.write("	\"parent\": \"item/generated\",\n");
			newJson.write("	\"textures\": {\n");
			newJson.write("		\"layer0\": \"tuom:items/" + item.getUnlocalizedName().substring(5) + "\"" +"\n");
			newJson.write("	}\n");
			newJson.write("}");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				newJson.flush();
				newJson.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
}