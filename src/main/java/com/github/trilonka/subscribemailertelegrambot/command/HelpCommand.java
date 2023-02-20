package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨<b>Дотупные команды</b>✨\n\n"
                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить статистику по использованию бота\n"
                    + "%s - подписаться на группу\n\n"
                    + "%s - получить помощь в работе со мной\n",
            START.getCommandName(), STOP.getCommandName(), STAT.getCommandName(),
            ADD_GROUP_SUB.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
