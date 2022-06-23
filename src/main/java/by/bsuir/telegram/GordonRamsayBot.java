package by.bsuir.telegram;

import by.bsuir.telegram.commands.HelpCommand;
import by.bsuir.telegram.commands.InsultGeneratorCommand;
import by.bsuir.telegram.commands.StartCommand;
import by.bsuir.telegram.commands.RandomVideoCommand;
import by.bsuir.telegram.service.UpdateService;
import by.bsuir.telegram.service.MessageService;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GordonRamsayBot extends TelegramLongPollingCommandBot {

	private final String BOT_USERNAME = System.getenv("BOT_USERNAME");
	private final String BOT_TOKEN = System.getenv("BOT_TOKEN");

	private final UpdateService updateService = new UpdateService();
	private final MessageService messageService = new MessageService();

	public GordonRamsayBot() {
		register(new StartCommand("/start", "get started"));
		register(new HelpCommand("/help",
				"get info about the bot and its commands"));
		register(new RandomVideoCommand("/random_video",
				"get a random Gordon Ramsay video"));
		register(new InsultGeneratorCommand("/generate_insult",
				"enrich your vocabulary with a custom-generated insult"));
	}

	@Override
	public String getBotUsername() {
		return BOT_USERNAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Override
	public void processNonCommandUpdate(Update update) {
		if (!update.hasCallbackQuery() && !update.hasMessage()) return;
		SendMessage sendMessage;
		if (update.hasCallbackQuery()) {
			String answer = updateService.handleCallback(update.getCallbackQuery());
			Long chatId = update.getCallbackQuery().getMessage().getChatId();
			sendMessage = messageService.createMessage(chatId, answer);
		} else {
			Long chatId = update.getMessage().getChatId();
			String answer = updateService.handleMessage(update.getMessage());
			sendMessage = messageService.createMessage(chatId, answer);
		}
		try {
			execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
