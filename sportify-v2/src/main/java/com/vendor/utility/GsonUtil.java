package com.vendor.utility;

public class GsonUtil {

	/**
	 * 
	 * Removes JSON object returned by DB execute method from the array.
	 * 
	 * @param results
	 *            - string representation JSON object in array
	 * @return string representation of single JSON object
	 */
	public static Object removeFromArray(Object results) {
		return ((String) results).substring(1, ((String) results).length() - 1);
	}

}
