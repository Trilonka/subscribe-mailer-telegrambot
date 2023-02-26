package com.github.trilonka.subscribemailertelegrambot.job;

import com.github.trilonka.subscribemailertelegrambot.service.FindNewPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewPostJob {

    private final FindNewPostService findNewPostService;

    public FindNewPostJob(FindNewPostService findNewPostService) {
        this.findNewPostService = findNewPostService;
    }

    @Scheduled(fixedRateString = "${bot.recountNewPostFixedRate}")
    public void findNewPosts() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new post job started");

        findNewPostService.findNewPosts();

        log.info("Find new posts job finished. Took seconds: {}",
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }

}
