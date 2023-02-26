package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.client.GroupClient;
import com.github.trilonka.subscribemailertelegrambot.service.GroupSubService;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.StatisticsService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {

    private CommandContainer commandContainer;
    private static final String USERNAME = "test";

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        GroupClient groupClient = Mockito.mock(GroupClient.class);
        StatisticsService statisticsService = Mockito.mock(StatisticsService.class);
        GroupSubService groupSubService = Mockito.mock(GroupSubService.class);
        commandContainer = new CommandContainer(
                sendBotMessageService, telegramUserService,
                groupClient, groupSubService, List.of(USERNAME), statisticsService);
    }

    @Test
    public void shouldGetAllExistingCommands() {
        // when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.findCommand(commandName.getCommandName(), USERNAME);
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        // given
        String unknownCommand = "/hfdser";

        // when
        Command command = commandContainer.findCommand(unknownCommand, USERNAME);

        // then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
