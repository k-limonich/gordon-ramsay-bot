package by.bsuir.telegram.commands;

import by.bsuir.telegram.service.MessageService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand extends BotCommand {

	private final MessageService messageService = new MessageService();

	public StartCommand(String commandIdentifier, String description) {
		super(commandIdentifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
		String text = "Hello, " + user.getUserName() + ", right? Good to see you!\n" +
				"Okay, let's get started! Use /help to get to know me better";
		SendMessage sendMessage = messageService.createMessage(chat.getId(), text);
		try {
			absSender.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
