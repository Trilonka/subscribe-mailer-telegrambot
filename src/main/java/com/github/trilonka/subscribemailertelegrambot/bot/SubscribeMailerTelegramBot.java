package com.github.trilonka.subscribemailertelegrambot.bot;

import com.github.trilonka.subscribemailertelegrambot.client.GroupClient;
import com.github.trilonka.subscribemailertelegrambot.command.CommandContainer;
import com.github.trilonka.subscribemailertelegrambot.service.GroupSubService;
import com.github.trilonka.subscribemailertelegrambot.service.StatisticsService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;
import com.github.trilonka.subscribemailertelegrambot.service.impl.SendBotMessageServiceBasic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.NO;

@Component
public class SubscribeMailerTelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    @Value("${bot.username}")
    private String username;

    public SubscribeMailerTelegramBot(@Value("${bot.token}") String token,
                                      TelegramUserService telegramUserService,
                                      GroupClient groupClient,
                                      GroupSubService groupSubService,
                                      StatisticsService statisticsService,
                                      @Value("#{'${bot.admins}'.split(',')}") List<String> admins)
    {
        super(token);
        this.commandContainer = new CommandContainer(
                new SendBotMessageServiceBasic(this),
                telegramUserService, groupClient, groupSubService, admins, statisticsService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            String username = update.getMessage().getFrom().getUserName();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(), username).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }
}
