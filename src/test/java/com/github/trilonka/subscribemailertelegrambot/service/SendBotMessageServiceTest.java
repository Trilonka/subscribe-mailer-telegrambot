package com.github.trilonka.subscribemailertelegrambot.service;

import com.github.trilonka.subscribemailertelegrambot.bot.SubscribeMailerTelegramBot;
import com.github.trilonka.subscribemailertelegrambot.service.impl.SendBotMessageServiceBasic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private SubscribeMailerTelegramBot bot;

    @BeforeEach
    public void init() {
        bot = Mockito.mock(SubscribeMailerTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceBasic(bot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException {
        // given
        Long chatId = 1L;
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        // when
        sendBotMessageService.sendMessage(chatId, message);

        // then
        Mockito.verify(bot).execute(sendMessage);
    }
}
