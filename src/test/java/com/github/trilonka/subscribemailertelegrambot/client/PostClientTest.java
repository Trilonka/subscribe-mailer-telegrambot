package com.github.trilonka.subscribemailertelegrambot.client;

import com.github.trilonka.subscribemailertelegrambot.client.dto.PostInfo;
import com.github.trilonka.subscribemailertelegrambot.client.impl.PostClientBasic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Integration-level testing for JavaRushPostClient")
public class PostClientTest {

    private final PostClient postClient =
            new PostClientBasic("https://javarush.com/api/1.0/rest");

    @Test
    public void shouldProperlyGetNew15Posts() {
        // when
        List<PostInfo> newPosts = postClient.findNewPosts(30, 2935);

        // then
        Assertions.assertEquals(15, newPosts.size());
    }
}
