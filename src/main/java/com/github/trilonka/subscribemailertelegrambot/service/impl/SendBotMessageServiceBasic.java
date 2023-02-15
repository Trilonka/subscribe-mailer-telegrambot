package com.github.trilonka.subscribemailertelegrambot.service.impl;

import com.github.trilonka.subscribemailertelegrambot.bot.SubscribeMailerTelegramBot;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class SendBotMessageServiceBasic implements SendBotMessageService {

    private final SubscribeMailerTelegramBot bot;

    public SendBotMessageServiceBasic(SubscribeMailerTelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableHtml(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO: add logging to the project
            e.printStackTrace();
        }
    }
}
