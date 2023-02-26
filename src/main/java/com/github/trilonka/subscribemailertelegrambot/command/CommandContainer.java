package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.client.GroupClient;
import com.github.trilonka.subscribemailertelegrambot.command.annotation.AdminCommand;
import com.github.trilonka.subscribemailertelegrambot.service.GroupSubService;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.StatisticsService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.*;
import static java.util.Objects.nonNull;

@Slf4j
public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            TelegramUserService telegramUserService,
                            GroupClient groupClient,
                            GroupSubService groupSubService,
                            List<String> admins,
                            StatisticsService statisticsService)
    {
        this.admins = admins;
        commandMap = Map.ofEntries(
                Map.entry(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService)),
                Map.entry(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService)),
                Map.entry(HELP.getCommandName(), new HelpCommand(sendBotMessageService)),
                Map.entry(NO.getCommandName(), new NoCommand(sendBotMessageService)),
                Map.entry(STAT.getCommandName(), new StatCommand(sendBotMessageService, statisticsService)),
                Map.entry(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(
                        sendBotMessageService, groupClient, groupSubService)),
                Map.entry(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(
                        telegramUserService, sendBotMessageService)),
                Map.entry(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(
                        sendBotMessageService, groupSubService, telegramUserService)),
                Map.entry(ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService)));
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandName, String username) {
        Command orDefault = commandMap.getOrDefault(commandName, unknownCommand);
        log.info("User {} uses /stat command", username);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
