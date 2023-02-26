package com.github.trilonka.subscribemailertelegrambot.service;

import com.github.trilonka.subscribemailertelegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {

    void save(TelegramUser user);

    Optional<TelegramUser> findByChatId(Long chatId);

    List<TelegramUser> findAllInActiveUsers();

    List<TelegramUser> findAllActiveUsers();
}
