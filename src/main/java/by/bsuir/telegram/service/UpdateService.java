package by.bsuir.telegram.service;

import by.bsuir.telegram.enums.CallbackType;
import by.bsuir.youtube.videos.HotelHell;
import by.bsuir.youtube.videos.KitchenNightmares;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateService {

	private final KitchenNightmares kitchenNightmares = new KitchenNightmares();
	private final HotelHell hotelHell = new HotelHell();

	private static final String INSULT_GENERATOR_REGEX = "([\\p{L}\\s]+,){2}([\\p{L}\\s]+)";
	private static final int INSULT_GENERATOR_INPUT_SIZE = 3;

	public String handleCallback(CallbackQuery callbackQuery) {
		CallbackType callbackType = CallbackType.valueOf(callbackQuery.getData());
		switch (callbackType) {
			case KITCHEN_NIGHTMARES_RANDOM:
				return kitchenNightmares.getRandomVideoLink();
			case HOTEL_HELL_RANDOM:
				return hotelHell.getRandomVideoLink();
			default:
				return "Oh dear, I think I can't answer to your request. Try again or use other commands";
		}
	}

	public String handleMessage(Message message) {
		String text = message.getText();
		Pattern pattern = Pattern.compile(INSULT_GENERATOR_REGEX);
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			String[] userInput = Arrays.stream(text.split(",")).map(String::trim)
					.map(String::toLowerCase).toArray(String[]::new);
			return userInput.length == INSULT_GENERATOR_INPUT_SIZE
					? InsultGenerator.getInsult(userInput)
					: "Format of the message is incorrect. Come on, it can't be so hard! " +
					"Moreover, I gave you an example, you donut!";
		}
		return "I don't understand your words, you donkey! Just use my commands!";
	}
}
