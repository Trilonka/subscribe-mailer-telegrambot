package com.github.trilonka.subscribemailertelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.trilonka.subscribemailertelegrambot.command.StopCommand.STOP_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest {

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService);
    }
}
