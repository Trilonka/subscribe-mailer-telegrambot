package com.github.trilonka.subscribemailertelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.trilonka.subscribemailertelegrambot.command.HelpCommand.HELP_MESSAGE;

@DisplayName("Unit-level testing for HelpCommand")
public class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
