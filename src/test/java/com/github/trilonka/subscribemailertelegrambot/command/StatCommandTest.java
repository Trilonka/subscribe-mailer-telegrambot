package com.github.trilonka.subscribemailertelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.trilonka.subscribemailertelegrambot.command.StatCommand.STAT_MESSAGE;

@DisplayName("Unit-level testing for StatCommand")
public class StatCommandTest extends AbstractCommandTest {

    @Override
    String getCommandMessage() {
        return String.format(STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatCommand(sendBotMessageService, telegramUserService);
    }
}
