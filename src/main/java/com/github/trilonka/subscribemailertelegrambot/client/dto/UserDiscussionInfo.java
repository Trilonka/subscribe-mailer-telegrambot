package com.github.trilonka.subscribemailertelegrambot.client.dto;

import lombok.Data;

@Data
public class UserDiscussionInfo {

    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
