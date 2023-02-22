package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.client.GroupClient;
import com.github.trilonka.subscribemailertelegrambot.service.GroupSubService;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;

import java.util.Map;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            GroupClient groupClient,
                            GroupSubService groupSubService)
    {
        commandMap = Map.ofEntries(
                Map.entry(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService)),
                Map.entry(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService)),
                Map.entry(HELP.getCommandName(), new HelpCommand(sendBotMessageService)),
                Map.entry(NO.getCommandName(), new NoCommand(sendBotMessageService)),
                Map.entry(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService)),
                Map.entry(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(
                        sendBotMessageService, groupClient, groupSubService)),
                Map.entry(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(
                        telegramUserService, sendBotMessageService)),
                Map.entry(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(
                        sendBotMessageService, groupSubService, telegramUserService)));
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandName) {
        return commandMap.getOrDefault(commandName, unknownCommand);
    }
}
