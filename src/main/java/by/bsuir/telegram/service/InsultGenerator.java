package by.bsuir.telegram.service;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InsultGenerator {

	private static final JSONArray firstPart;
	private static final JSONArray secondPart;
	private static final JSONArray thirdPart;

	private static final String part1FilePath = "src/main/resources/generator/part-1.json";
	private static final String part2FilePath = "src/main/resources/generator/part-2.json";
	private static final String part3FilePath = "src/main/resources/generator/part-3.json";

	static {
		firstPart = readFileToJSONArray(part1FilePath);
		secondPart = readFileToJSONArray(part2FilePath);
		thirdPart = readFileToJSONArray(part3FilePath);
	}

	public static String getInsult(String... words) {
		final char SPACE = ' ';
		int firstPartIndex = Math.abs(words[0].hashCode()) % firstPart.length();
		int secondPartIndex = Math.abs(words[1].hashCode()) % secondPart.length();
		int thirdPartIndex = Math.abs(words[2].hashCode()) % thirdPart.length();
		return firstPart.getString(firstPartIndex) +
				SPACE + secondPart.getString(secondPartIndex) +
				SPACE + thirdPart.getString(thirdPartIndex);
	}

	private static JSONArray readFileToJSONArray(String path) {
		try {
			String fileString = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
			return new JSONArray(fileString);
		} catch (IOException e) {
			e.printStackTrace();
			return new JSONArray();
		}
	}

}
