package com.github.trilonka.subscribemailertelegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class SubscribeMailerTelegramBot extends TelegramLongPollingBot {

    @Value("${BOT_USERNAME}")
    private String username;
    public SubscribeMailerTelegramBot(@Value("${BOT_TOKEN}") String token) {
        super(token);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(message);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                // TODO: add logging to the project
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return this.username;
    }
}
