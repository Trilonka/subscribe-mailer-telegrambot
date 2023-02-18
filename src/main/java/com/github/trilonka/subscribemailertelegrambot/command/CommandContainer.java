package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;

import java.util.Map;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        commandMap = Map.ofEntries(
                Map.entry(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService)),
                Map.entry(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService)),
                Map.entry(HELP.getCommandName(), new HelpCommand(sendBotMessageService)),
                Map.entry(NO.getCommandName(), new NoCommand(sendBotMessageService)),
                Map.entry(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService)));
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandName) {
        return commandMap.getOrDefault(commandName, unknownCommand);
    }
}
