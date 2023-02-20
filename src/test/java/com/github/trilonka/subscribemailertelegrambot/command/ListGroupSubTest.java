package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.repository.entity.GroupSub;
import com.github.trilonka.subscribemailertelegrambot.repository.entity.TelegramUser;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import com.github.trilonka.subscribemailertelegrambot.service.TelegramUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.LIST_GROUP_SUB;

@DisplayName("Unit-level testing for ListGroupSubCommand")
public class ListGroupSubTest {

    @Test
    public void shouldProperlyShowListOfSubs() {
        // given
        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(1L);
        Mockito.when(message.getText()).thenReturn(LIST_GROUP_SUB.getCommandName());
        update.setMessage(message);

        TelegramUser user = new TelegramUser();
        user.setActive(true);
        user.setChatId("1");

        List<GroupSub> groupSubs = new ArrayList<>();
        groupSubs.add(populateGroupSub(1, "first"));
        groupSubs.add(populateGroupSub(2, "second"));
        groupSubs.add(populateGroupSub(3, "third"));

        user.setGroupSubs(groupSubs);

        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);

        Mockito.when(telegramUserService.findByChatId(user.getChatId())).thenReturn(Optional.of(user));

        ListGroupSubCommand listGroupSubCommand = new ListGroupSubCommand(telegramUserService, sendBotMessageService);

        String expectedMessage = "Я нашел все подписки на группы: \n\n" +
                groupSubs.stream()
                        .map(it -> "Группа: " + it.getTitle() + " , ID = " + it.getId() + " \n")
                        .collect(Collectors.joining());

        // when
        listGroupSubCommand.execute(update);

        // then
        Mockito.verify(sendBotMessageService).sendMessage(user.getChatId(), expectedMessage);
    }

    private GroupSub populateGroupSub(Integer id, String title) {
        GroupSub groupSub = new GroupSub();
        groupSub.setId(id);
        groupSub.setTitle(title);
        return groupSub;
    }
}
