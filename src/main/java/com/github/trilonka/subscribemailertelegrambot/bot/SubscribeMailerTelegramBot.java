package com.github.trilonka.subscribemailertelegrambot.bot;

import com.github.trilonka.subscribemailertelegrambot.command.CommandContainer;
import com.github.trilonka.subscribemailertelegrambot.service.impl.SendBotMessageServiceBasic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.NO;

@Component
public class SubscribeMailerTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    @Value("${bot.username}")
    private String username;

    public SubscribeMailerTelegramBot(@Value("${bot.token}") String token) {
        super(token);
        this.commandContainer = new CommandContainer(new SendBotMessageServiceBasic(this));
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }
}
