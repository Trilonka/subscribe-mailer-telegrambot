package com.github.trilonka.subscribemailertelegrambot.service;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
}
