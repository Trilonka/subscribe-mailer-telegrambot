package com.github.trilonka.subscribemailertelegrambot.client.impl;

import com.github.trilonka.subscribemailertelegrambot.client.PostClient;
import com.github.trilonka.subscribemailertelegrambot.client.dto.PostInfo;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostClientBasic implements PostClient {

    private final String javarushPpiPostPath;

    public PostClientBasic(@Value("${javarush.api.path}") String javarushPpiPostPath) {
        this.javarushPpiPostPath = javarushPpiPostPath + "/posts";
    }

    @Override
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId) {
        List<PostInfo> lastPostsByGroup = Unirest.get(javarushPpiPostPath)
                .queryString("order", "NEW")
                .queryString("groupKid", groupId)
                .queryString("limit", 15)
                .asObject(new GenericType<List<PostInfo>>() {})
                .getBody();
        List<PostInfo> newPosts = new ArrayList<>();
        for (PostInfo post : lastPostsByGroup) {
            if (lastPostId.equals(post.getId())) {
                return newPosts;
            }
            newPosts.add(post);
        }
        return newPosts;
    }
}
