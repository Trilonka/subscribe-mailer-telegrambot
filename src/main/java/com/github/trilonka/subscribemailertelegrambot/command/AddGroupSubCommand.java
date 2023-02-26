package com.github.trilonka.subscribemailertelegrambot.command;

import com.github.trilonka.subscribemailertelegrambot.client.GroupClient;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupDiscussionInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupRequestArgs;
import com.github.trilonka.subscribemailertelegrambot.repository.entity.GroupSub;
import com.github.trilonka.subscribemailertelegrambot.service.GroupSubService;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.github.trilonka.subscribemailertelegrambot.command.CommandName.ADD_GROUP_SUB;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class AddGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final GroupClient groupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService,
                              GroupClient groupClient,
                              GroupSubService groupSubService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.groupClient = groupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        String message = update.getMessage().getText().trim();
        if (message.equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(update.getMessage().getChatId());
            return;
        }
        String groupId = message.split(" ")[1];
        Long chatId = update.getMessage().getChatId();
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = groupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Подписал на группу " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }

    private void sendGroupNotFound(Long chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(Long chatId) {
        String groupIds = groupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());
        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выберай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";
        sendBotMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}
