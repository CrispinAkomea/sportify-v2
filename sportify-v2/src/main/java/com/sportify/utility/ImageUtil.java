package com.sportify.utility;

import java.io.InputStream;

import com.vendor.App;

/**
 * 
 * Utility class with methods that are return InputStreams from which images are
 * loaded. Numerous methods for different sources.
 * 
 * @author Crispin
 *
 */
public class ImageUtil {

	public static InputStream getImage(Object client, String image) {
		return client.getClass().getResourceAsStream(App.getResourcePath() + image);
	}

	public static InputStream getIcon(Object client, String image) {
		return client.getClass().getResourceAsStream(App.getResourcePathImgIcon() + image);
	}

	public static InputStream getBadge(Object client, String image) {
		return client.getClass().getResourceAsStream(App.getResourcePathImgBadge() + image);
	}

}
