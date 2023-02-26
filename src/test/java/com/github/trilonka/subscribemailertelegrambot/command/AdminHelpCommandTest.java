package com.github.trilonka.subscribemailertelegrambot.command;

import org.junit.jupiter.api.DisplayName;

import static com.github.trilonka.subscribemailertelegrambot.command.AdminHelpCommand.ADMIN_HELP_MESSAGE;

@DisplayName("Unit-level testing for AdminHelpCommand")
public class AdminHelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandMessage() {
        return ADMIN_HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AdminHelpCommand(sendBotMessageService);
    }
}
