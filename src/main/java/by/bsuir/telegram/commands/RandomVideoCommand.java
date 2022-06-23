package by.bsuir.telegram.commands;

import by.bsuir.telegram.service.MessageService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static by.bsuir.telegram.enums.CallbackType.HOTEL_HELL_RANDOM;
import static by.bsuir.telegram.enums.CallbackType.KITCHEN_NIGHTMARES_RANDOM;

public class RandomVideoCommand extends BotCommand {

	private final MessageService messageService = new MessageService();

	public RandomVideoCommand(String commandIdentifier, String description) {
		super(commandIdentifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
		ReplyKeyboard replyMarkup = getReplyMarkup();
		String text = "All right big boy, what are we watching today?";
		SendMessage sendMessage = messageService.createMessage(chat.getId(), text, replyMarkup);
		try {
			absSender.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private InlineKeyboardMarkup getReplyMarkup() {
		List<List<InlineKeyboardButton>> buttons = Arrays.asList(
				Collections.singletonList(
						InlineKeyboardButton.builder()
								.text("\uD83E\uDDC6 Kitchen Nightmares | FULL EPISODE")
								.callbackData(KITCHEN_NIGHTMARES_RANDOM.toString())
								.build()),
				Collections.singletonList(
						InlineKeyboardButton.builder()
								.text("\uD83D\uDECE Hotel Hell | FULL EPISODE")
								.callbackData(HOTEL_HELL_RANDOM.toString())
								.build()));
		return InlineKeyboardMarkup.builder().keyboard(buttons).build();
	}
}
