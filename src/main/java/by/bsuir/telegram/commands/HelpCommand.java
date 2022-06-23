package by.bsuir.telegram.commands;

import by.bsuir.telegram.service.MessageService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {

	private final MessageService messageService = new MessageService();

	public HelpCommand(String commandIdentifier, String description) {
		super(commandIdentifier, description);
	}

	@Override
	public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
		String instructions = "Okay, a few words about me. I am GordonRamsayBot! " +
				"I can provide you with some of the finest content with the one and only - " +
				"Chef Ramsay \uD83D\uDC68\u200D\uD83C\uDF73" +
				"\n\nList of my commands:\n" +
				"/random_video - get a random Gordon Ramsay youtube video\n" +
				"/generate_insult - generate a custom Gordon Ramsay insult\n" +
				"\nThat's about it, more commands are coming soon.\nHave fun, you donkey!";
		SendMessage sendMessage = messageService.createMessage(chat.getId(), instructions);
		try {
			absSender.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
