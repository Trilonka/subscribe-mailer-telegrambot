package com.github.trilonka.subscribemailertelegrambot.client.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
public class GroupInfo {

    String avatarUrl;
    String createTime;
    String description;
    Integer id;
    String key;
    Integer levelToEditor;
    MeGroupInfo meGroupInfo;
    String pictureUrl;
    String title;
    MeGroupInfoType type;
    Integer usersCount;
    GroupVisibilityStatus visibilityStatus;
}
