package com.vendor.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonBuilderUtil {

	/**
	 * 
	 * Creates and returns a Gson objects with the specific settings. Disabled HTML
	 * escaping and pretty printing format.
	 * 
	 * @return new Gson object with specific settings
	 */
	public static Gson createGson() {
		return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	}

}
