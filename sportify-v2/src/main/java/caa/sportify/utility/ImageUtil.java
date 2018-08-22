package caa.sportify.utility;

import java.io.InputStream;

import caa.sportify.Config;

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
		return client.getClass().getResourceAsStream(Config.getResourcePath() + image);
	}

	public static InputStream getIcon(Object client, String image) {
		return client.getClass().getResourceAsStream(Config.getResourcePathImgIcon() + image);
	}

	public static InputStream getBadge(Object client, String image) {
		return client.getClass().getResourceAsStream(Config.getResourcePathImgBadge() + image);
	}

}
