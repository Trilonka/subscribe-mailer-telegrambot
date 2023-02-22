package com.github.trilonka.subscribemailertelegrambot.service;

import com.github.trilonka.subscribemailertelegrambot.client.dto.GroupDiscussionInfo;
import com.github.trilonka.subscribemailertelegrambot.repository.entity.GroupSub;

import java.util.Optional;

public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);

    GroupSub save(GroupSub groupSub);

    Optional<GroupSub> findById(Integer id);
}
