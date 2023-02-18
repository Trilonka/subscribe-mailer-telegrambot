package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String STAT_MESSAGE = "Sub Mailer Telegram Bot использует %s человек.";

    public StatCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = telegramUserService.retrieveAllActiveUsers().size();
        sendBotMessageService.sendMessage(
                update.getMessage().getChatId().toString(),
                String.format(STAT_MESSAGE, activeUserCount));
    }
}
