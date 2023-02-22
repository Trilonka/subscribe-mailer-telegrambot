package com.github.trilonka.subscribemailertelegrambot.client;

import com.github.trilonka.subscribemailertelegrambot.client.dto.PostInfo;

import java.util.List;

public interface PostClient {

    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}
