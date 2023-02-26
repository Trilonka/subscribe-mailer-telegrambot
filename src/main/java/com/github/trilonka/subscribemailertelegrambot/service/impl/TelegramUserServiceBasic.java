package com.github.trilonka.subscribemailertelegrambot.service.impl;

import com.github.trilonka.subscribemailertelegrambot.repository.TelegramUserRepository;
import com.github.trilonka.subscribemailertelegrambot.repository.entity.TelegramUser;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TelegramUserServiceBasic implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void save(TelegramUser user) {
        telegramUserRepository.save(user);
    }

    @Override
    public Optional<TelegramUser> findByChatId(Long chatId) {
        return telegramUserRepository.findById(chatId);
    }

    @Override
    public List<TelegramUser> findAllInActiveUsers() {
        return telegramUserRepository.findAllByActiveFalse();
    }

    @Override
    public List<TelegramUser> findAllActiveUsers() {
        return telegramUserRepository.findAllByActiveTrue();
    }
}
