package com.github.trilonka.subscribemailertelegrambot.service.impl;

import com.github.trilonka.subscribemailertelegrambot.client.PostClient;
import com.github.trilonka.subscribemailertelegrambot.client.dto.PostInfo;
import com.github.trilonka.subscribemailertelegrambot.repository.entity.GroupSub;
import com.github.trilonka.subscribemailertelegrambot.repository.entity.TelegramUser;
import com.github.trilonka.subscribemailertelegrambot.service.FindNewArticleService;
import com.github.trilonka.subscribemailertelegrambot.service.GroupSubService;
import com.github.trilonka.subscribemailertelegrambot.service.SendBotMessageService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindNewArticleServiceBasic implements FindNewArticleService {

    public static final String JAVARUSH_WEB_POST_FORMAT = "https://javarush.com/groups/posts/%s";

    private final GroupSubService groupSubService;
    private final PostClient postClient;
    private final SendBotMessageService sendBotMessageService;

    public FindNewArticleServiceBasic(GroupSubService groupSubService,
                                      PostClient postClient,
                                      SendBotMessageService sendBotMessageService)
    {
        this.groupSubService = groupSubService;
        this.postClient = postClient;
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(gSub -> {
            List<PostInfo> newPosts = postClient.findNewPosts(gSub.getId(), gSub.getLastArticleId());

            setNewLastArticleId(gSub, newPosts);

            notifySubscribersAboutNewArticles(gSub, newPosts);
        });
    }

    private void setNewLastArticleId(GroupSub gSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    gSub.setLastArticleId(id);
                    groupSubService.save(gSub);
                });
    }

    private void notifySubscribersAboutNewArticles(GroupSub gSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewArticles = newPosts.stream()
                .map(post -> String.format("✨Вышла новая статья <b>%s</b> в группе <b>%s</b>.✨\n\n" +
                                "<b>Описание:</b> %s\n\n" +
                                "<b>Ссылка:</b> %s\n",
                        post.getTitle(), gSub.getTitle(), post.getDescription(), getPostUrl(post.getKey())))
                .collect(Collectors.toList());
        gSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendBotMessageService.sendMessage(it.getChatId(), messagesWithNewArticles));
    }

    private String getPostUrl(String key) {
        return String.format(JAVARUSH_WEB_POST_FORMAT, key);
    }
}
