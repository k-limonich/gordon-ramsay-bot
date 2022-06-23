package by.bsuir.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropsUtils {

	private static final String LINKS_PROPERTIES_PATH = "src/main/resources/youtubeinfo.properties";

	public static String getString(String property) {
		String value = "";
		try (InputStream inputStream = new FileInputStream(LINKS_PROPERTIES_PATH)) {
			Properties properties = new Properties();
			properties.load(inputStream);
			value = properties.getProperty(property);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static int getInt(String property) {
		return Integer.parseInt(getString(property));
	}
}
