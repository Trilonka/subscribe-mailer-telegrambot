package com.github.trilonka.subscribemailertelegrambot.repository;

import com.github.trilonka.subscribemailertelegrambot.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles(profiles = {"test"})
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TelegramUserRepositoryTest {

    @Autowired
    private TelegramUserRepository telegramUserRepository;

    @Sql(scripts = {"/sql/clearDbs.sql", "/sql/telegram_users.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers() {
        // when
        List<TelegramUser> users = telegramUserRepository.findAllByActiveTrue();

        // then
        Assertions.assertEquals(4, users.size());
    }

    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveTelegramUser() {
        // given
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChatId("12317");
        telegramUser.setActive(false);
        telegramUserRepository.save(telegramUser);

        // when
        Optional<TelegramUser> saved = telegramUserRepository.findById(telegramUser.getChatId());

        // then
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser, saved.get());
    }
}
