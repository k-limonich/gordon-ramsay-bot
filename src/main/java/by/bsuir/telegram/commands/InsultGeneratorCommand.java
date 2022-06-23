package by.bsuir.telegram.commands;

import by.bsuir.telegram.service.MessageService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InsultGeneratorCommand extends BotCommand {

	private final MessageService messageService = new MessageService();

	public InsultGeneratorCommand(String commandIdentifier, String description) {
		super(commandIdentifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
		String text = "Okay, looks like you want to generate your unique insult, " +
				"which Gordon Ramsay could use while talking to you. " +
				"Unfortunately, all I know about you is your name " +
				"(and your IP address \uD83D\uDE0A)" +
				"\nSo why don't you tell me more about yourself! " +
				"\nInput following information in this format:\n\n" +
				"your home country, your favourite color, your favourite food";
		SendMessage sendMessage = messageService.createMessage(chat.getId(), text);
		try {
			absSender.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
