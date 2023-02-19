package com.github.trilonka.subscribemailertelegrambot.client;

import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupCountRequestArgs;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupDiscussionInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupInfo;
import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupRequestArgs;

import java.util.List;

public interface JavaRushGroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    Integer getGroupCount(GroupCountRequestArgs countRequestArgs);

    GroupDiscussionInfo getGroupById(Integer id);

    Integer findLastPostId(Integer groupSub);
}
