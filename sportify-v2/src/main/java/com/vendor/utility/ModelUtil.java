package com.vendor.utility;

public class ModelUtil {

	/**
	 * 
	 * Converts string representation of JSON object to java object.
	 * 
	 * @param json
	 *            - string representation of JSON object
	 * @param classOfT
	 *            - the class to which object is deserialized
	 * @return object of class T
	 */
	public static <T> T toModel(String json, Class<T> classOfT) {
		return GsonBuilderUtil.createGson().fromJson(json, classOfT);
	}

	/**
	 * 
	 * Converts string representation of JSON objects to array of java objects.
	 * 
	 * @param json
	 *            - string representation of JSON object
	 * @param classOfT
	 *            - the class to which object is deserialized
	 * @return array of java objects of class T
	 */
	public static <T> T[] toModels(String json, Class<T[]> classOfT) {
		return GsonBuilderUtil.createGson().fromJson(json, classOfT);
	}

}
