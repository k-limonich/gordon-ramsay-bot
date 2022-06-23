package by.bsuir.telegram.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageService {

	public SendMessage createMessage(Long chatId, String text) {
		return SendMessage.builder()
				.text(text)
				.chatId(chatId.toString())
				.build();
	}

	public SendMessage createMessage(Long chatId, String text, ReplyKeyboard replyMarkup) {
		return SendMessage.builder()
				.text(text)
				.chatId(chatId.toString())
				.replyMarkup(replyMarkup)
				.build();
	}

	public SendMessage createIllegalActionMessage(Long chatId) {
		String text = "What the fuck are you talking about, you muppet? " +
				"I don't understand your stupid words, use my commands!";
		return SendMessage.builder()
				.text(text)
				.chatId(chatId.toString())
				.build();
	}

	public SendMessage createErrorMessage(Long chatId) {
		String text = "Fuck me, not quite sure what the fuck have you just done. " +
				"Just be a good boy and use registered commands!";
		return SendMessage.builder()
				.text(text)
				.chatId(chatId.toString())
				.build();
	}
}
