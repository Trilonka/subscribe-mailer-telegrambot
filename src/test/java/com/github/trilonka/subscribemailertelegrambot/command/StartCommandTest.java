package com.github.trilonka.subscribemailertelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.trilonka.subscribemailertelegrambot.command.StartCommand.START_MESSAGE;

@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest {

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService, telegramUserService);
    }
}
