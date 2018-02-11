package com.vendor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

	private static String name;
	private static String database;
	private static String resourcePath;
	private static String resourcePathTemp;
	private static String resourcePathImgBadge;
	private static String resourcePathImgIcon;
	private static String statisticsUrl;
	private static String fixturesUrl;
	private static final Logger logger = LogManager.getLogger(App.class);

	/**
	 * 
	 * Static initialization block loads configuration properties from the
	 * properties file located in the resources folder.
	 * 
	 * Use the app.cfg.xml file to keep any other general application configurations
	 * you need. Create the fields and load them in the try block.
	 * 
	 */
	static {
		Properties properties = new Properties();
		try (InputStream config = App.class.getClassLoader().getResourceAsStream("app.cfg.xml")) {
			properties.loadFromXML(config);
			name = properties.getProperty("app.name");
			database = properties.getProperty("connection.url");
			resourcePath = properties.getProperty("resources.path");
			resourcePathTemp = properties.getProperty("resources.path.tmp");
			resourcePathImgBadge = properties.getProperty("resources.path.img.badge");
			resourcePathImgIcon = properties.getProperty("resources.path.img.icon");
			statisticsUrl = properties.getProperty("statistics.url");
			fixturesUrl = properties.getProperty("fixtures.url");
		} catch (IOException e) {
			logger.error("CONFIG_LOG - Failure to load app configuration properties. (" + e.getMessage() + ")");
			e.printStackTrace();
		}
	}

	public static String getName() {
		return name;
	}

	public static String getDatabase() {
		return database;
	}

	public static String getResourcePath() {
		return resourcePath;
	}

	public static String getResourcePathTemp() {
		return resourcePathTemp;
	}

	public static String getResourcePathImgBadge() {
		return resourcePathImgBadge;
	}

	public static String getResourcePathImgIcon() {
		return resourcePathImgIcon;
	}

	public static String getStatisticsUrl() {
		return statisticsUrl;
	}

	public static String getFixturesUrl() {
		return fixturesUrl;
	}

}
