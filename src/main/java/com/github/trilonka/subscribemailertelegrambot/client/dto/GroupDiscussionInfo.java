package com.github.trilonka.subscribemailertelegrambot.client.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level= AccessLevel.PRIVATE)
public class GroupDiscussionInfo extends GroupInfo {

    Integer commentsCount;
    UserDiscussionInfo userDiscussionInfo;
}
