package caa.sportify.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class HttpUtil {

	public static void downloadFile(String fromFile, String toFile) throws MalformedURLException, IOException {
		FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
	}

}