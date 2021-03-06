package main;

import items.AG;
import items.Order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {

	public List<String> cookies;
	public String Username;
	public String Password;
	public ArrayList<AG> AGs;
	public ArrayList<Order> Orders;

	public Config() {
		cookies = new ArrayList<String>();
		Username = "Borjan";
		Password = "bnmopützu";
		AGs = new ArrayList<AG>();
		Orders = new ArrayList<Order>();
	}

	private static Config instance;

	public static Config getInstance() {
		if (instance == null) {
			instance = fromDefaults();
		}
		return instance;
	}

	public static void load(File file) {
		instance = fromFile(file);

		// no config file found
		if (instance == null) {
			instance = fromDefaults();
		}
	}

	public static void load(String file) {
		load(new File(file));
	}

	private static Config fromDefaults() {
		Config config = new Config();
		return config;
	}

	public void toFile(String file) {
		toFile(new File(file));
	}

	public void toFile(File file) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonConfig = gson.toJson(this);
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			writer.write(jsonConfig);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Config fromFile(File configFile) {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(configFile)));
			return gson.fromJson(reader, Config.class);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}
}
