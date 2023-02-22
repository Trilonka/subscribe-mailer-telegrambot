package com.github.trilonka.subscribemailertelegrambot.job;

import com.github.trilonka.subscribemailertelegrambot.service.FindNewArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewArticleJob {

    private final FindNewArticleService findNewArticleService;

    public FindNewArticleJob(FindNewArticleService findNewArticleService) {
        this.findNewArticleService = findNewArticleService;
    }

    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
    public void findNewArticles() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started");

        findNewArticleService.findNewArticles();

        log.info("Find new articles job finished. Took seconds: {}",
                LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }

}
