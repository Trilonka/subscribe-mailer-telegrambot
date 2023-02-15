package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.bot.SubscribeMailerTelegramBot;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.impl.SendBotMessageServiceBasic;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {

    protected SubscribeMailerTelegramBot bot = Mockito.mock(SubscribeMailerTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceBasic(bot);

    abstract String getCommandMessage();
    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException {
        // given
        Long chatId = 123456789L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        // when
        getCommand().execute(update);

        // then
        Mockito.verify(bot).execute(sendMessage);
    }
}
